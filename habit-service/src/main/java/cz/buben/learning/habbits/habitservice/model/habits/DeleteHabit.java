package cz.buben.learning.habbits.habitservice.model.habits;

import cz.buben.learning.habbits.habitservice.repository.HabitRepository;
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
