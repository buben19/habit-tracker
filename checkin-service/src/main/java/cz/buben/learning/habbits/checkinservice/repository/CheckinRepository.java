package cz.buben.learning.habbits.checkinservice.repository;

import cz.buben.learning.habbits.checkinservice.domain.Checkin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckinRepository extends JpaRepository<Checkin, Long> {
  List<Checkin> findByUserIdAndDay(Long userId, java.time.LocalDate day);

  List<Checkin> findByHabitId(Long habitId);
}
