package cz.buben.learning.habbits.habitservice.model.habits;

import cz.buben.learning.habbits.habitservice.UserIdProvider;
import cz.buben.learning.habbits.habitservice.domain.Habit;
import cz.buben.learning.habbits.habitservice.repository.HabitRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateHabit {

  private final HabitRepository habitRepository;
  private final UserIdProvider userIdProvider;

  @Transactional
  public Habit create(Habit habit) {
    userIdProvider.getCurrentUserId().ifPresentOrElse(habit::setUserId, () -> {;
      throw new RuntimeException("Cannot create habit - no authenticated user found");
    });
    return habitRepository.save(habit);
  }
}
