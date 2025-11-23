package cz.buben.learning.habbits.habitservice.model.habits;

import cz.buben.learning.habbits.habitservice.UserIdProvider;
import cz.buben.learning.habbits.habitservice.client.CheckinClient;
import cz.buben.learning.habbits.habitservice.domain.Habit;
import cz.buben.learning.habbits.habitservice.mapping.HabitMapper;
import cz.buben.learning.habbits.habitservice.repository.HabitRepository;
import cz.buben.learning.habits.common.dto.CheckinDto;
import cz.buben.learning.habits.common.dto.HabitCompleteResponse;
import cz.buben.learning.habits.common.dto.HabitDto;
import cz.buben.learning.habits.common.dto.HabitsCompleteResponse;
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
  private final UserIdProvider userIdProvider;
  private final HabitMapper habitMapper;

  @Transactional
  public HabitsCompleteResponse get() {
    String userId = userIdProvider.getCurrentUserId().orElseThrow(
        () -> new RuntimeException("Cannot get habits - no authenticated user found"));
    List<Habit> habits = habitRepository.findByUserId(userId);
    List<HabitCompleteResponse> habitCompleteResponses = new ArrayList<>();
    habits.forEach(habit -> {
      HabitDto habitDto = habitMapper.entityToDto(habit);
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
