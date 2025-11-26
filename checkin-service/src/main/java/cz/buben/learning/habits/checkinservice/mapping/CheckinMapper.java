package cz.buben.learning.habits.checkinservice.mapping;

import cz.buben.learning.habits.checkinservice.domain.Checkin;
import cz.buben.learning.habits.common.dto.CheckinDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CheckinMapper {
  Checkin dtoToEntity(CheckinDto checkinDto);
  CheckinDto entityToDto(Checkin checkin);
}
