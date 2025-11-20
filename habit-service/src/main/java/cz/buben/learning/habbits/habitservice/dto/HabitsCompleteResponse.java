package cz.buben.learning.habbits.habitservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class HabitsCompleteResponse {
  private List<HabitCompleteResponse> habits;
}
