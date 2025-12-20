package cz.buben.learning.habits.habitservice.model.habit;

import cz.buben.learning.habits.common.dto.HabitDto;
import cz.buben.learning.habits.habitservice.domain.Habit;
import cz.buben.learning.habits.habitservice.dto.UpdateHabitDtoIn;
import cz.buben.learning.habits.habitservice.mapping.HabitMapper;
import cz.buben.learning.habits.habitservice.repository.HabitRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Update {

  private final HabitRepository habitRepository;
  private final HabitMapper habitMapper;

  @Transactional
  public HabitDto update(Long id, UpdateHabitDtoIn updateHabitDtoIn) {
    Habit loaded = habitRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Habit with id " + id + " not found"));

    Habit build = loaded.toBuilder()
        .name(updateHabitDtoIn.getName())
        .description(updateHabitDtoIn.getDescription())
        .schedule(updateHabitDtoIn.getSchedule())
        .build();

    Habit save = habitRepository.save(build);
    return habitMapper.entityToDto(save);
  }
}
