package cz.buben.learning.habits.habitservice.model.habits;

import cz.buben.learning.habits.habitservice.domain.Habit;
import cz.buben.learning.habits.habitservice.mapping.HabitMapper;
import cz.buben.learning.habits.habitservice.repository.HabitRepository;
import cz.buben.learning.habits.common.dto.HabitDto;
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
