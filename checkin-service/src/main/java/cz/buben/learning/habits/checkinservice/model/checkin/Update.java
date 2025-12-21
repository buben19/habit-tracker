package cz.buben.learning.habits.checkinservice.model.checkin;

import cz.buben.learning.habits.checkinservice.domain.Checkin;
import cz.buben.learning.habits.checkinservice.dto.CheckinDtoIn;
import cz.buben.learning.habits.checkinservice.mapping.CheckinMapper;
import cz.buben.learning.habits.checkinservice.repository.CheckinRepository;
import cz.buben.learning.habits.common.dto.CheckinDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Update {

  private final CheckinRepository checkinRepository;
  private final CheckinMapper checkinMapper;

  @Transactional
  public CheckinDto update(Long id, CheckinDtoIn checkinDtoIn) {
    Checkin found = checkinRepository.findById(id).orElseThrow(
        () -> new RuntimeException("Checkin not found"));

    Checkin build = found.toBuilder()
        .day(checkinDtoIn.getDay())
        .habitId(checkinDtoIn.getHabitId())
        .userId(checkinDtoIn.getUserId())
        .build();

    Checkin save = checkinRepository.save(build);
    return checkinMapper.entityToDto(save);
  }
}
