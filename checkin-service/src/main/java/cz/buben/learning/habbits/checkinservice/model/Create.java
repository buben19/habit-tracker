package cz.buben.learning.habbits.checkinservice.model;

import cz.buben.learning.habbits.checkinservice.domain.Checkin;
import cz.buben.learning.habbits.checkinservice.repository.CheckinRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Create {

  private final CheckinRepository checkinRepository;

  @Transactional
  public Checkin createCheckin(Checkin checkin) {
    return checkinRepository.save(checkin);
  }
}
