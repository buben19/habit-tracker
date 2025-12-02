package cz.buben.learning.habits.habitservice.model.habits;

import cz.buben.learning.habits.common.dto.*;
import cz.buben.learning.habits.habitservice.UserIdProvider;
import cz.buben.learning.habits.habitservice.client.CheckinClient;
import cz.buben.learning.habits.habitservice.client.UserClient;
import cz.buben.learning.habits.habitservice.domain.Habit;
import cz.buben.learning.habits.habitservice.mapping.HabitMapper;
import cz.buben.learning.habits.habitservice.repository.HabitRepository;
import io.micrometer.context.ContextSnapshotFactory;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.task.SimpleAsyncTaskSchedulerBuilder;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.concurrent.SimpleAsyncTaskScheduler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetHabitWithCheckins {

  private final HabitRepository habitRepository;
  private final CheckinClient checkinClient;
  private final UserClient userClient;
  private final UserIdProvider userIdProvider;
  private final HabitMapper habitMapper;
  @Qualifier("applicationTaskExecutor")
  private final AsyncTaskExecutor asyncTaskExecutor;

  @Transactional
  public HabitsCompleteResponse getHabitsWithCheckins() {
    String userId = userIdProvider.getCurrentUserId().orElseThrow(
        () -> new RuntimeException("Cannot get habits - no authenticated user found"));
    UserDto userById = userClient.findUserById(userId);
    List<Habit> habits = habitRepository.findByUserId(userId);
    Map<Long, List<CheckinDto>> checkinsForHabits = getCheckinsForHabits(habits);
    List<HabitCompleteResponse> habitCompleteResponses = new ArrayList<>();
    habits.forEach(habit -> {
      HabitDto habitDto = habitMapper.entityToDto(habit);
      habitDto.setUserName(userById.getFirstName() + " " + userById.getLastName());
      List<CheckinDto> checkinsByHabitId = checkinsForHabits.get(habit.getId());
      HabitCompleteResponse build = HabitCompleteResponse.builder()
          .habit(habitDto)
          .checkin(checkinsByHabitId)
          .build();
      habitCompleteResponses.add(build);
    });
    return HabitsCompleteResponse.builder()
        .habits(habitCompleteResponses)
        .build();
  }

  private Map<Long, List<CheckinDto>> getCheckinsForHabits(List<Habit> habits) {
    TaskDecorator taskDecorator = taskDecorator();
    try (SimpleAsyncTaskScheduler simpleAsyncTaskScheduler = new SimpleAsyncTaskSchedulerBuilder()
        .taskDecorator(taskDecorator)
        .build()) {

      Map<Long, Future<List<CheckinDto>>> futureMap = habits.stream()
          .collect(
              Collectors.toMap(
                  Habit::getId,
                  habit -> CompletableFuture.supplyAsync(
                      () -> checkinClient.getCheckinsByHabitId(habit.getId()),
                      simpleAsyncTaskScheduler
                  )
              )
          );

      return futureMap.entrySet().stream()
          .collect(
              Collectors.toMap(
                  Map.Entry::getKey,
                  entry -> {
                    try {
                      return entry.getValue().get();
                    } catch (Exception e) {
                      throw new RuntimeException("Failed to get checkins for habit id: " + entry.getKey(), e);
                    }
                  }
              )
          );
    }
  }

  private TaskDecorator taskDecorator() {
    return runnable -> ContextSnapshotFactory
        .builder()
        .build()
        .captureAll()
        .wrap(runnable);
  }
}
