package cz.buben.learning.habbits.checkinservice.mapping;

import cz.buben.learning.habbits.checkinservice.domain.Checkin;
import cz.buben.learning.habits.common.dto.CheckinDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CheckinMapper {
  Checkin dtoToEntity(CheckinDto checkinDto);
  CheckinDto entityToDto(Checkin checkin);
}
