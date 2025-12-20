package cz.buben.learning.habits.checkinservice.controller.checkin;

import cz.buben.learning.habits.checkinservice.model.checkin.CreateToday;
import cz.buben.learning.habits.common.dto.CheckinDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/checkins")
@AllArgsConstructor
public class CreateTodayForHabitController {

  private final CreateToday createToday;

  @Operation(
      summary = "Create today's check-in for a habit",
      description = "Create a check-in record for today associated with a specific habit ID"
  )
  @PostMapping("/today/{habitId}")
  public CheckinDto createTodayCheckin(@PathVariable Long habitId) {
    return createToday.createTodayCheckin(habitId);
  }
}
