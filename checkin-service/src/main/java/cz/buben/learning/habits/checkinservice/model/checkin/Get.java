package cz.buben.learning.habits.checkinservice.model.checkin;

import cz.buben.learning.habits.checkinservice.domain.Checkin;
import cz.buben.learning.habits.checkinservice.mapping.CheckinMapper;
import cz.buben.learning.habits.checkinservice.repository.CheckinRepository;
import cz.buben.learning.habits.common.dto.CheckinDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Get {

  private final CheckinRepository checkinRepository;
  private final CheckinMapper checkinMapper;

  public CheckinDto get(Long id) {
    Checkin checkin = checkinRepository.findById(id).orElseThrow(
        () -> new RuntimeException("Checkin not found"));
    return checkinMapper.entityToDto(checkin);
  }
}
