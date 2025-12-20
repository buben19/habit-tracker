package cz.buben.learning.habits.checkinservice.model.checkin;

import cz.buben.learning.habits.checkinservice.mapping.CheckinMapper;
import cz.buben.learning.habits.checkinservice.repository.CheckinRepository;
import cz.buben.learning.habits.common.dto.CheckinDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetAll {

  private final CheckinRepository checkinRepository;
  private final CheckinMapper checkinMapper;

  @Transactional
  public List<CheckinDto> getAllCheckins() {
    return checkinRepository.findAll().stream()
        .map(checkinMapper::entityToDto)
        .collect(Collectors.toList());
  }
}
