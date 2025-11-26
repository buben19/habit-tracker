package cz.buben.learning.habits.habitservice.mapping;

import cz.buben.learning.habits.habitservice.domain.Habit;
import cz.buben.learning.habits.common.dto.HabitDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HabitMapper {
  Habit dtoToEntity(HabitDto habitDto);
  HabitDto entityToDto(Habit habit);
}
