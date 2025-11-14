package cz.buben.learning.habbits.checkinservice.model;

import cz.buben.learning.habbits.checkinservice.domain.Checkin;
import cz.buben.learning.habbits.checkinservice.repository.CheckinRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAll {

  private final CheckinRepository checkinRepository;

  @Transactional
  public List<Checkin> getAllCheckins() {
    return checkinRepository.findAll();
  }
}
