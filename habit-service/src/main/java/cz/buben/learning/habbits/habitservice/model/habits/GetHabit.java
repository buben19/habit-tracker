package cz.buben.learning.habbits.habitservice.model.habits;

import cz.buben.learning.habbits.habitservice.domain.Habit;
import cz.buben.learning.habbits.habitservice.dto.HabitDto;
import cz.buben.learning.habbits.habitservice.mapping.HabitMapper;
import cz.buben.learning.habbits.habitservice.repository.HabitRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetHabit {

  private final HabitRepository habitRepository;
  private final HabitMapper habitMapper;

  @Transactional
  public HabitDto get(Long id) {
    Habit habit = habitRepository.findById(id)
        .orElseThrow();
    return habitMapper.entityToDto(habit);
  }
}
