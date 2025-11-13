package cz.buben.learning.habbits.habitservice.repository;

import cz.buben.learning.habbits.habitservice.domain.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitRepository extends JpaRepository<Habit, Long> {
  List<Habit> findByUserId(Long userId);
}
