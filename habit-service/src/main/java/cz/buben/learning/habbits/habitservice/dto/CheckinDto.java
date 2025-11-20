package cz.buben.learning.habbits.habitservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CheckinDto {

  private Long id;
  private Long habitId;
  private Long userId;
  private LocalDate day;
}
