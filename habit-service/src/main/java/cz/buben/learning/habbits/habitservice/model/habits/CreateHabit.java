package cz.buben.learning.habbits.habitservice.model.habits;

import cz.buben.learning.habbits.habitservice.UserIdProvider;
import cz.buben.learning.habbits.habitservice.domain.Habit;
import cz.buben.learning.habbits.habitservice.dto.HabitDto;
import cz.buben.learning.habbits.habitservice.mapping.HabitMapper;
import cz.buben.learning.habbits.habitservice.repository.HabitRepository;
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
    userIdProvider.getCurrentUserId().ifPresentOrElse(habit::setUserId, () -> {;
      throw new RuntimeException("Cannot create habit - no authenticated user found");
    });
    Habit save = habitRepository.save(habit);
    return habitMapper.entityToDto(save);
  }
}
