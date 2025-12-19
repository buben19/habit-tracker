package cz.buben.learning.habits.habitservice.controller;

import cz.buben.learning.habits.common.dto.HabitDto;
import cz.buben.learning.habits.common.dto.HabitsCompleteResponse;
import cz.buben.learning.habits.habitservice.model.habits.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Habits")
@AllArgsConstructor
public class HabitController {

  private final GetAllHabits getAllHabits;
  private final GetHabit getHabit;
  private final CreateHabit createHabit;
  private final UpdateHabit updateHabit;
  private final DeleteHabit deleteHabit;
  private final GetHabitWithCheckins getHabitWithCheckins;

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
    return getAllHabits.all();
  }

  @Operation(
      summary = "Get habit by ID",
      description = "Retrieve a specific habit by its ID",
      parameters = @Parameter(
          name = "id",
          in = ParameterIn.PATH,
          description = "ID of the habit to retrieve",
          required = true
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
  @GetMapping("/{id}")
  public HabitDto getHabit(@PathVariable Long id) {
    return getHabit.get(id);
  }

  @Operation(
      summary = "Create a new habit",
      description = "Create a new habit with the provided details"
  )
  @PostMapping
  public HabitDto createHabit(@Valid @RequestBody HabitDto habit) {
    return createHabit.create(habit);
  }

  @Operation(
      summary = "Update an existing habit",
      description = "Update the details of an existing habit"
  )
  @PutMapping("/{id}")
  public ResponseEntity<HabitDto> updateHabit(@Valid @RequestBody HabitDto habit) {
    HabitDto updatedHabit = updateHabit.update(habit);
    return ResponseEntity.ok(updatedHabit);
  }

  @Operation(
      summary = "Delete a habit",
      description = "Delete a habit by its ID"
  )
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteHabit(@PathVariable Long id) {
    deleteHabit.delete(id);
    return ResponseEntity.noContent().build();
  }

  @Operation(
      summary = "Get habits with check-ins",
      description = "Retrieve habits along with their associated check-ins. Only habits for current user are returned."
  )
  @GetMapping("/with-checkins")
  public HabitsCompleteResponse getHabitsWithCheckins() {
    return getHabitWithCheckins.getHabitsWithCheckins();
  }
}
