package cz.buben.learning.habbits.habitservice.model.habits;

import cz.buben.learning.habbits.habitservice.UserIdProvider;
import cz.buben.learning.habbits.habitservice.domain.Habit;
import cz.buben.learning.habbits.habitservice.dto.HabitDto;
import cz.buben.learning.habbits.habitservice.mapping.HabitMapper;
import cz.buben.learning.habbits.habitservice.repository.HabitRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetHabits {

  private final HabitRepository habitRepository;
  private final UserIdProvider userIdProvider;
  private final HabitMapper habitMapper;

  @Transactional
  public List<HabitDto> all() {
    String userId = userIdProvider.getCurrentUserId().orElseThrow(
        () -> new RuntimeException("Cannot get habits - no authenticated user found"));
    List<Habit> habits = habitRepository.findByUserId(userId);
    return habits.stream()
        .map(habitMapper::entityToDto)
        .collect(Collectors.toList());
  }
}
