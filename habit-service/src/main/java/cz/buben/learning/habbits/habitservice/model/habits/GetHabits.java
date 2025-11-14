package cz.buben.learning.habbits.habitservice.model.habits;

import cz.buben.learning.habbits.habitservice.domain.Habit;
import cz.buben.learning.habbits.habitservice.repository.HabitRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetHabits {

  private final HabitRepository habitRepository;

  @Transactional
  public List<Habit> all() {
    return habitRepository.findAll();
  }
}
