package cz.buben.learning.habits.habitservice.controller.habit;

import cz.buben.learning.habits.common.dto.HabitsCompleteResponse;
import cz.buben.learning.habits.habitservice.model.habits.GetHabitsWithCheckins;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/habits")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Habits")
@AllArgsConstructor
public class GetHabitsWithCheckinsController {

  private final GetHabitsWithCheckins getHabitsWithCheckins;

  @Operation(
      summary = "Get habits with check-ins",
      description = "Retrieve habits along with their associated check-ins. Only habits for current user are returned.",
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
  @GetMapping("/with-checkins")
  public HabitsCompleteResponse getHabitsWithCheckins() {
    return getHabitsWithCheckins.getHabitsWithCheckins();
  }
}
