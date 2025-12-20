package cz.buben.learning.habits.checkinservice.controller.checkin;

import cz.buben.learning.habits.checkinservice.model.checkin.GetAll;
import cz.buben.learning.habits.common.dto.CheckinDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/checkins")
@AllArgsConstructor
public class GetAllController {

  private final GetAll getAll;

  @Operation(
      summary = "Get all check-ins",
      description = "Retrieve a list of all check-ins"
  )
  @GetMapping
  public List<CheckinDto> getAllCheckins() {
    return getAll.getAllCheckins();
  }
}
