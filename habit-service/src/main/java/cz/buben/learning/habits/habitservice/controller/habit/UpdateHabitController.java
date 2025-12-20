package cz.buben.learning.habits.habitservice.controller.habit;

import cz.buben.learning.habits.common.dto.HabitDto;
import cz.buben.learning.habits.habitservice.dto.UpdateHabitDtoIn;
import cz.buben.learning.habits.habitservice.model.habit.UpdateHabit;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/habits")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Habits")
@AllArgsConstructor
public class UpdateHabitController {

  private final UpdateHabit updateHabit;

  @Operation(
      summary = "Update an existing habit",
      description = "Update the details of an existing habit",
      parameters = @Parameter(
          name = "id",
          in = ParameterIn.PATH,
          description = "ID of the habit to update",
          required = true,
          example = "1"
      ),
      requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
          content = @Content(
              mediaType = "application/json",
              examples = @ExampleObject(
                  name = "Update habit",
                  summary = "Update test habit",
                  description = "Example request to update a new habit",
                  value = "{ " +
                      "\"id\": 1, " +
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
  @PutMapping("/{id}")
  public ResponseEntity<HabitDto> updateHabit(@PathVariable Long id, @Valid @RequestBody UpdateHabitDtoIn habit) {
    HabitDto updatedHabit = updateHabit.update(id, habit);
    return ResponseEntity.ok(updatedHabit);
  }
}
