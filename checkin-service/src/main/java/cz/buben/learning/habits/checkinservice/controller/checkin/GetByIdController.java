package cz.buben.learning.habits.checkinservice.controller.checkin;

import cz.buben.learning.habits.common.dto.CheckinDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/checkins")
@AllArgsConstructor
public class GetByIdController {

  @Operation(
      summary = "Get check-in by ID",
      description = "Retrieve a specific check-in by its ID"
  )
  @GetMapping("/{id}")
  public CheckinDto getCheckinById(@PathVariable Long id) {
    throw new UnsupportedOperationException("Not implemented yet");
  }
}
