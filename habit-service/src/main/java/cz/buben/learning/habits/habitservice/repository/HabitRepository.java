package cz.buben.learning.habits.habitservice.repository;

import cz.buben.learning.habits.habitservice.domain.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitRepository extends JpaRepository<Habit, Long> {
  List<Habit> findByUserId(String userId);
}
