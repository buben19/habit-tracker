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
public class DeleteController {

  @Operation(
      summary = "Delete a check-in",
      description = "Delete a check-in by its ID"
  )
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    throw new UnsupportedOperationException("Not implemented yet");
  }
}
