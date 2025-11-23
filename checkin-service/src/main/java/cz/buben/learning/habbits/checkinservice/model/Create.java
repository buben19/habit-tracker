package cz.buben.learning.habbits.checkinservice.model;

import cz.buben.learning.habbits.checkinservice.domain.Checkin;
import cz.buben.learning.habbits.checkinservice.mapping.CheckinMapper;
import cz.buben.learning.habbits.checkinservice.repository.CheckinRepository;
import cz.buben.learning.habits.common.dto.CheckinDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Create {

  private final CheckinRepository checkinRepository;
  private final CheckinMapper checkinMapper;

  @Transactional
  public CheckinDto createCheckin(CheckinDto checkinDto) {
    Checkin checkin = checkinMapper.dtoToEntity(checkinDto);
    Checkin saved = checkinRepository.save(checkin);
    return checkinMapper.entityToDto(saved);
  }
}
