package cz.buben.learning.habits.habitservice.mapping;

import cz.buben.learning.habits.habitservice.domain.Habit;
import cz.buben.learning.habits.habitservice.dto.CreateHabitDtoIn;
import cz.buben.learning.habits.habitservice.dto.UpdateHabitDtoIn;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UpdateHabitMapper {
  Habit dtoToEntity(UpdateHabitDtoIn updateHabitDtoIn);
}
