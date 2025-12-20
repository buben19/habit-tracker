package cz.buben.learning.habits.checkinservice.controller.checkin;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/checkins")
@AllArgsConstructor
public class DeleteAllForHabitController {

  @Operation(
      summary = "Delete all check-ins for a habit",
      description = "Delete all check-ins associated with a specific habit ID"
  )
  @DeleteMapping("/habit/{habitId}")
  public ResponseEntity<Void> deleteAllCheckinsForHabit(@PathVariable Long habitId) {
    throw new UnsupportedOperationException("Not implemented yet");
  }
}
