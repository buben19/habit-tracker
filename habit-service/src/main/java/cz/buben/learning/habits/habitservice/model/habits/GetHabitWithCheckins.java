package cz.buben.learning.habits.habitservice.model.habits;

import cz.buben.learning.habits.common.dto.*;
import cz.buben.learning.habits.habitservice.UserIdProvider;
import cz.buben.learning.habits.habitservice.client.CheckinClient;
import cz.buben.learning.habits.habitservice.client.UserClient;
import cz.buben.learning.habits.habitservice.domain.Habit;
import cz.buben.learning.habits.habitservice.mapping.HabitMapper;
import cz.buben.learning.habits.habitservice.repository.HabitRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GetHabitWithCheckins {

  private final HabitRepository habitRepository;
  private final CheckinClient checkinClient;
  private final UserClient userClient;
  private final UserIdProvider userIdProvider;
  private final HabitMapper habitMapper;

  @Transactional
  public HabitsCompleteResponse getHabitsWithCheckins() {
    String userId = userIdProvider.getCurrentUserId().orElseThrow(
        () -> new RuntimeException("Cannot get habits - no authenticated user found"));
    UserDto userById = userClient.findUserById(userId);
    List<Habit> habits = habitRepository.findByUserId(userId);
    List<HabitCompleteResponse> habitCompleteResponses = new ArrayList<>();
    habits.forEach(habit -> {
      HabitDto habitDto = habitMapper.entityToDto(habit);
      habitDto.setUserName(userById.getFirstName() + " " + userById.getLastName());
      List<CheckinDto> checkinsByHabitId = checkinClient.getCheckinsByHabitId(habit.getId());
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
}
