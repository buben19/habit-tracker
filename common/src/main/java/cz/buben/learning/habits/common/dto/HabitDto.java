package cz.buben.learning.habits.common.dto;

import lombok.Data;

@Data
public class HabitDto {

  private Long id;
  private Long userId;
  private String name;
  private String description;
  private String schedule;
}
