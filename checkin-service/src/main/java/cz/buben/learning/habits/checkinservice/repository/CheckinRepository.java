package cz.buben.learning.habits.checkinservice.repository;

import cz.buben.learning.habits.checkinservice.domain.Checkin;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

@NullMarked
public interface CheckinRepository extends JpaRepository<Checkin, Long> {
  List<Checkin> findByUserIdAndDay(String userId, LocalDate day);

  List<Checkin> findByHabitId(Long habitId);
}
