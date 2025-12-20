package cz.buben.learning.habits.checkinservice.controller.checkin;

import cz.buben.learning.habits.checkinservice.model.checkin.Today;
import cz.buben.learning.habits.common.dto.CheckinDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "checkin")
@RequestMapping("/api/checkins")
@AllArgsConstructor
public class CreateTodayController {

  private final Today today;

  @Operation(
      summary = "Get today's check-ins for a user",
      description = "Retrieve a list of check-ins made today by a specific user"
  )
  @GetMapping("/today")
  public List<CheckinDto> today(@RequestParam String userId) {
    return today.getTodayCheckins(userId);
  }
}
