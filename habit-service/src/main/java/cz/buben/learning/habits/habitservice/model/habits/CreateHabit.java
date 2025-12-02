package cz.buben.learning.habits.habitservice.model.habits;

import cz.buben.learning.habits.habitservice.UserIdProvider;
import cz.buben.learning.habits.habitservice.domain.Habit;
import cz.buben.learning.habits.habitservice.mapping.HabitMapper;
import cz.buben.learning.habits.habitservice.repository.HabitRepository;
import cz.buben.learning.habits.common.dto.HabitDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateHabit {

  private final HabitRepository habitRepository;
  private final UserIdProvider userIdProvider;
  private final HabitMapper habitMapper;

  @Transactional
  public HabitDto create(HabitDto habitDto) {
    Habit habit = habitMapper.dtoToEntity(habitDto);
    userIdProvider.getCurrentUserId().ifPresentOrElse(habit::setUserId, () -> {
      throw new RuntimeException("Cannot create habit - no authenticated user found");
    });
    Habit save = habitRepository.save(habit);
    return habitMapper.entityToDto(save);
  }
}
