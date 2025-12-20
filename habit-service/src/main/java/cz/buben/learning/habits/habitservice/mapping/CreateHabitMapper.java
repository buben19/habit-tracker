package cz.buben.learning.habits.habitservice.mapping;

import cz.buben.learning.habits.habitservice.domain.Habit;
import cz.buben.learning.habits.habitservice.dto.CreateHabitDtoIn;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreateHabitMapper {
  Habit dtoToEntity(CreateHabitDtoIn createHabitDtoIn);
}
