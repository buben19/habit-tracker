package cz.buben.learning.habits.habitservice.model.habits;

import cz.buben.learning.habits.habitservice.repository.HabitRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteHabit {

  private final HabitRepository habitRepository;

  @Transactional
  public void delete(Long id) {
    habitRepository.deleteById(id);
  }
}
