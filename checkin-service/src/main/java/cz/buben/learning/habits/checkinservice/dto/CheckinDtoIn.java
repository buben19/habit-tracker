package cz.buben.learning.habits.checkinservice.dto;

import cz.buben.learning.habits.common.dto.CheckinDto;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * @see CheckinDto
 */
@Data
@Builder
public class CheckinDtoIn {

  @NotNull(message = "Habit ID is mandatory")
  private Long habitId;
  private String userId;
  private LocalDate day;
}
