package cz.buben.learning.habits.checkinservice.controller.checkin;

import cz.buben.learning.habits.checkinservice.dto.CheckinDtoIn;
import cz.buben.learning.habits.checkinservice.model.checkin.Update;
import cz.buben.learning.habits.common.dto.CheckinDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "checkin")
@RequestMapping("/api/checkins")
@AllArgsConstructor
public class UpdateController {

  private final Update update;

  @Operation(
      summary = "Update an existing check-in",
      description = "Update the details of an existing check-in"
  )
  @PutMapping("/{id}")
  public ResponseEntity<CheckinDto> update(@PathVariable Long id, @Valid @RequestBody CheckinDtoIn checkinDtoIn) {
    CheckinDto updated = update.update(id, checkinDtoIn);
    return ResponseEntity.ok(updated);
  }
}
