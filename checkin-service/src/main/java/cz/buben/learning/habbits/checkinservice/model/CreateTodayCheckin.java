package cz.buben.learning.habbits.checkinservice.model;

import cz.buben.learning.habbits.checkinservice.UserIdProvider;
import cz.buben.learning.habbits.checkinservice.domain.Checkin;
import cz.buben.learning.habbits.checkinservice.mapping.CheckinMapper;
import cz.buben.learning.habbits.checkinservice.repository.CheckinRepository;
import cz.buben.learning.habits.common.dto.CheckinDto;
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
  private final UserIdProvider userIdProvider;
  private final CheckinMapper checkinMapper;

  @Transactional
  public CheckinDto createTodayCheckin(Long habitId) {
    String userId = userIdProvider.getCurrentUserId().orElseThrow(
        () -> new RuntimeException("User not authenticated"));

    Checkin checkin = Checkin.builder()
        .habitId(habitId)
        .userId(userId)
        .day(LocalDate.now())
        .build();
    Checkin saved = checkinRepository.save(checkin);
    return checkinMapper.entityToDto(saved);
  }
}
