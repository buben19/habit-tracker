package cz.buben.learning.habits.habitservice.model.habit;

import cz.buben.learning.habits.habitservice.repository.HabitRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Delete {

  private final HabitRepository habitRepository;

  @Transactional
  public void delete(Long id) {
    habitRepository.deleteById(id);
  }
}
