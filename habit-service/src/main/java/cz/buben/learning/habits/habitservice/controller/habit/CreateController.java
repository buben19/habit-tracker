package cz.buben.learning.habits.habitservice.controller.habit;

import cz.buben.learning.habits.common.dto.HabitDto;
import cz.buben.learning.habits.habitservice.dto.CreateHabitDtoIn;
import cz.buben.learning.habits.habitservice.model.habit.Create;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/habits")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Habits")
@AllArgsConstructor
public class CreateController {

  private final Create create;

  @Operation(
      summary = "Create a new habit",
      description = "Create a new habit with the provided details",
      requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
          content = @Content(
              mediaType = "application/json",
              examples = @ExampleObject(
                  name = "Create habit",
                  summary = "Create test habit",
                  description = "Example request to create a new habit",
                  value = "{ " +
                      "\"name\": \"Read Books\", " +
                      "\"description\": \"Read at least 30 minutes daily\", " +
                      "\"schedule\": \"DAILY\" }"

              )
          )
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
  @PostMapping
  public HabitDto createHabit(@Valid @RequestBody CreateHabitDtoIn habit) {
    return create.create(habit);
  }
}
