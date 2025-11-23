package cz.buben.learning.habits.common.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckinDto {

  private Long id;

  @NotNull(message = "Habit ID is mandatory")
  private Long habitId;
  private String userId;
  private LocalDate day;
}
