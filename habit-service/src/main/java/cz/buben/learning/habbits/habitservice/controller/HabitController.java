package cz.buben.learning.habbits.habitservice.controller;

import cz.buben.learning.habbits.habitservice.dto.HabitDto;
import cz.buben.learning.habbits.habitservice.dto.HabitsCompleteResponse;
import cz.buben.learning.habbits.habitservice.model.habits.*;
import io.swagger.v3.oas.annotations.Operation;
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

  private final GetHabits getHabits;
  private final GetHabit getHabit;
  private final CreateHabit createHabit;
  private final UpdateHabit updateHabit;
  private final DeleteHabit deleteHabit;
  private final GetHabitWithCheckins getHabitWithCheckins;

  @Operation(
      summary = "Get all habits",
      description = "Retrieve a list of all habits",
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
    return getHabits.all();
  }

  @Operation(
      summary = "Get habit by ID",
      description = "Retrieve a specific habit by its ID"
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

  @GetMapping("/with-checkins")
  public HabitsCompleteResponse getHabitsWithCheckins() {
    return getHabitWithCheckins.get();
  }
}
