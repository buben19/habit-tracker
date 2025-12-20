package cz.buben.learning.habits.habitservice.model.habit;

import cz.buben.learning.habits.habitservice.UserIdProvider;
import cz.buben.learning.habits.habitservice.domain.Habit;
import cz.buben.learning.habits.habitservice.mapping.HabitMapper;
import cz.buben.learning.habits.habitservice.repository.HabitRepository;
import cz.buben.learning.habits.common.dto.HabitDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetAll {

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
