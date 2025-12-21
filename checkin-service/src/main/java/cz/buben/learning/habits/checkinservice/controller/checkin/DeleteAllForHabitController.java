package cz.buben.learning.habits.checkinservice.controller.checkin;

import cz.buben.learning.habits.common.dto.DeleteCheckinDtoOut;
import cz.buben.learning.habits.checkinservice.model.checkin.DeleteAllForHabit;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "checkin")
@RequestMapping("/api/checkins")
@AllArgsConstructor
public class DeleteAllForHabitController {

  private final DeleteAllForHabit deleteAllForHabit;

  @Operation(
      summary = "Delete all check-ins for a habit",
      description = "Delete all check-ins associated with a specific habit ID"
  )
  @DeleteMapping("/habit/{habitId}")
  public DeleteCheckinDtoOut deleteAllCheckinsForHabit(@PathVariable Long habitId) {
    return deleteAllForHabit.deleteAllForHabit(habitId);
  }
}
