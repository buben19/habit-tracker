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
public class UpdateHabit {

  private final HabitRepository habitRepository;
  private final HabitMapper habitMapper;

  @Transactional
  public HabitDto update(HabitDto habitDto) {
    Habit habit = habitMapper.dtoToEntity(habitDto);
    Habit save = habitRepository.save(habit);
    return habitMapper.entityToDto(save);
  }
}
