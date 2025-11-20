package cz.buben.learning.habbits.habitservice.model.habits;

import cz.buben.learning.habbits.habitservice.checkin.CheckinClient;
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

  @Transactional
  public HabitsCompleteResponse get() {
    List<Habit> habits = habitRepository.findAll();
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
