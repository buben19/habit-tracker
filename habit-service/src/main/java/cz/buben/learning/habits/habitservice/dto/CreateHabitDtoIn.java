package cz.buben.learning.habits.habitservice.dto;

import cz.buben.learning.habits.common.dto.HabitDto;
import cz.buben.learning.habits.habitservice.domain.Schedule;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @see HabitDto
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Data transfer object for creating a new habit")
public class CreateHabitDtoIn {

  @Schema(
      description = "Name of the habit",
      example = "Morning Jog"
  )
  @NotBlank(message = "Name is mandatory")
  private String name;

  @Schema(
      description = "Description of the habit",
      example = "Jog for 30 minutes every morning"
  )
  private String description;

  @Schema(
      description = "Schedule for the habit"
  )
  @NotNull(message = "Schedule is mandatory")
  private Schedule schedule;
}
