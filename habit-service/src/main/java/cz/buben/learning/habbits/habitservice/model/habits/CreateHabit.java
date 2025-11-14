package cz.buben.learning.habbits.habitservice.model.habits;

import cz.buben.learning.habbits.habitservice.domain.Habit;
import cz.buben.learning.habbits.habitservice.repository.HabitRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateHabit {

  private final HabitRepository habitRepository;

  @Transactional
  public Habit create(Habit habit) {
    return habitRepository.save(habit);
  }
}
