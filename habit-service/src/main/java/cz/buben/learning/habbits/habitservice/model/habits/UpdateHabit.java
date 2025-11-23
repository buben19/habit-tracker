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
