package cz.buben.learning.habbits.checkinservice.model;

import cz.buben.learning.habbits.checkinservice.domain.Checkin;
import cz.buben.learning.habbits.checkinservice.repository.CheckinRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class CreateTodayCheckin {

  private final CheckinRepository checkinRepository;

  @Transactional
  public Checkin createTodayCheckin(Long habitId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null) {
      String userId = authentication.getName();
      Checkin checkin = Checkin.builder()
          .habitId(habitId)
          .userId(userId)
          .day(LocalDate.now())
          .build();
      return checkinRepository.save(checkin);
    } else {
      throw new RuntimeException("User not authenticated");
    }
  }
}
