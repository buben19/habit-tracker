package cz.buben.learning.habits.checkinservice.controller.checkin;

import cz.buben.learning.habits.checkinservice.dto.CheckinDtoIn;
import cz.buben.learning.habits.checkinservice.model.checkin.Create;
import cz.buben.learning.habits.common.dto.CheckinDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "checkin")
@RequestMapping("/api/checkins")
@AllArgsConstructor
public class CreateController {

  private final Create create;

  @Operation(
      summary = "Create a new check-in",
      description = "Create a new check-in record"
  )
  @PostMapping
  public CheckinDto create(@Valid @RequestBody CheckinDtoIn checkinDtoIn) {
    return create.createCheckin(checkinDtoIn);
  }
}
