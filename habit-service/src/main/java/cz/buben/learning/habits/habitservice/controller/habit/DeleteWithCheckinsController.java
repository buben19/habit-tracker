package cz.buben.learning.habits.habitservice.controller.habit;

import cz.buben.learning.habits.habitservice.dto.DeleteHabitWithCheckinsDtoOut;
import cz.buben.learning.habits.habitservice.model.habit.DeleteWithCheckins;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/habits")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "habit")
@AllArgsConstructor
public class DeleteWithCheckinsController {

  private final DeleteWithCheckins deleteWithCheckins;

  @Operation(
      summary = "Delete habit with check-ins",
      description = "Delete a habit along with all its associated check-ins. Not implemented yet.",
      responses = {
          @ApiResponse(
              responseCode = "501",
              description = "Not Implemented"
          )
      }
  )
  @DeleteMapping("/with-checkins/{habitId}")
  @PreAuthorize("hasRole('client_user')")
  public DeleteHabitWithCheckinsDtoOut deleteHabitWithCheckins(@PathVariable Long habitId) {
    return deleteWithCheckins.deleteWithCheckins(habitId);
  }
}
