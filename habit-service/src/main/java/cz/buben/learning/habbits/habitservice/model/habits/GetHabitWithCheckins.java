package cz.buben.learning.habbits.habitservice.model.habits;

import cz.buben.learning.habbits.habitservice.UserIdProvider;
import cz.buben.learning.habbits.habitservice.client.CheckinClient;
import cz.buben.learning.habbits.habitservice.domain.Habit;
import cz.buben.learning.habbits.habitservice.dto.CheckinDto;
import cz.buben.learning.habbits.habitservice.dto.HabitCompleteResponse;
import cz.buben.learning.habbits.habitservice.dto.HabitsCompleteResponse;
import cz.buben.learning.habbits.habitservice.repository.HabitRepository;
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

  @Transactional
  public HabitsCompleteResponse get() {
    String userId = userIdProvider.getCurrentUserId().orElseThrow(
        () -> new RuntimeException("Cannot get habits - no authenticated user found"));
    List<Habit> habits = habitRepository.findByUserId(userId);
    List<HabitCompleteResponse> habitCompleteResponses = new ArrayList<>();
    habits.forEach(habit -> {
      List<CheckinDto> checkinsByHabitId = checkinClient.getCheckinsByHabitId(habit.getId());
      HabitCompleteResponse build = HabitCompleteResponse.builder()
          .habit(habit)
          .checkin(checkinsByHabitId)
          .build();
      habitCompleteResponses.add(build);
    });
    return HabitsCompleteResponse.builder()
        .habits(habitCompleteResponses)
        .build();
  }
}
