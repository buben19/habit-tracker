package cz.buben.learning.habits.habitservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteHabitWithCheckinsDtoOut {

  private final Integer deletedCheckins;
}
