package cz.buben.learning.habits.common.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HabitDto {

  private Long id;
  private String userId;
  private String userName;

  @NotBlank(message = "Name is mandatory")
  private String name;
  private String description;
  private String schedule;
}