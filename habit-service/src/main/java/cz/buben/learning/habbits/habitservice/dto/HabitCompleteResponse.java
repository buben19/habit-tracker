package cz.buben.learning.habbits.habitservice.dto;

import cz.buben.learning.habbits.habitservice.domain.Habit;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class HabitCompleteResponse {
  private Habit habit;
  private List<CheckinDto> checkin;
}
