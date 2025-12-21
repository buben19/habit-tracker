package cz.buben.learning.habits.checkinservice.model.checkin;

import cz.buben.learning.habits.checkinservice.repository.CheckinRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Delete {

  private final CheckinRepository checkinRepository;

  @Transactional
  public void delete(Long id) {
    checkinRepository.deleteById(id);
  }
}
