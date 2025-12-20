package cz.buben.learning.habits.habitservice.controller.habit;

import cz.buben.learning.habits.common.dto.HabitDto;
import cz.buben.learning.habits.habitservice.model.habit.GetAll;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "habit")
@AllArgsConstructor
public class GetAllController {

  private final GetAll getAll;

  @Operation(
      summary = "Get all habits",
      description = "Retrieve a list of all habits",
      requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
          description = "No request body is needed for this operation"
      ),
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Successful operation"
          ),
          @ApiResponse(
              responseCode = "401",
              description = "Unauthorized"
          )
      }
  )
  @GetMapping
  public List<HabitDto> getHabits() {
    return getAll.all();
  }
}
