package cz.buben.learning.habits.checkinservice.controller.checkin;

import cz.buben.learning.habits.checkinservice.model.checkin.FindByHabitId;
import cz.buben.learning.habits.common.dto.CheckinDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "checkin")
@RequestMapping("/api/checkins")
@AllArgsConstructor
public class FindByHabitIdController {

  private final FindByHabitId findByHabitId;

  @Operation(
      summary = "Find check-ins by habit ID",
      description = "Retrieve a list of check-ins associated with a specific habit ID"
  )
  @GetMapping("/habit/{habitId}")
  public List<CheckinDto> findCheckinsByByHabitId(@PathVariable Long habitId) {
    return findByHabitId.findCheckinsByHabitId(habitId);
  }
}
