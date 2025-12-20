package cz.buben.learning.habits.checkinservice.controller;

import cz.buben.learning.habits.checkinservice.model.*;
import cz.buben.learning.habits.common.dto.CheckinDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checkins")
@AllArgsConstructor
public class CheckinController {

  private final GetAll getAll;
  private final Create create;
  private final FindCheckinsByHabitId findCheckinsByHabitId;
  private final Today today;
  private final CreateTodayCheckin createTodayCheckin;

  @Operation(
      summary = "Get all check-ins",
      description = "Retrieve a list of all check-ins"
  )
  @GetMapping
  public List<CheckinDto> getAllCheckins() {
    return getAll.getAllCheckins();
  }

  @Operation(
      summary = "Find check-ins by habit ID",
      description = "Retrieve a list of check-ins associated with a specific habit ID"
  )
  @GetMapping("/habit/{habitId}")
  public List<CheckinDto> findCheckinsByByHabitId(@PathVariable Long habitId) {
    return findCheckinsByHabitId.findCheckinsByHabitId(habitId);
  }

  @Operation(
      summary = "Get check-in by ID",
      description = "Retrieve a specific check-in by its ID"
  )
  @GetMapping("/{id}")
  public CheckinDto getCheckinById(@PathVariable Long id) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  @Operation(
      summary = "Create a new check-in",
      description = "Create a new check-in record"
  )
  @PostMapping
  public CheckinDto create(@Valid @RequestBody CheckinDto checkin) {
    return create.createCheckin(checkin);
  }

  @Operation(
      summary = "Update an existing check-in",
      description = "Update the details of an existing check-in"
  )
  @PutMapping("/{id}")
  public ResponseEntity<CheckinDto> update(@PathVariable Long id, @Valid @RequestBody CheckinDto checkin) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  @Operation(
      summary = "Delete a check-in",
      description = "Delete a check-in by its ID"
  )
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  @Operation(
      summary = "Get today's check-ins for a user",
      description = "Retrieve a list of check-ins made today by a specific user"
  )
  @GetMapping("/today")
  public List<CheckinDto> today(@RequestParam String userId) {
    return today.getTodayCheckins(userId);
  }

  @Operation(
      summary = "Create today's check-in for a habit",
      description = "Create a check-in record for today associated with a specific habit ID"
  )
  @PostMapping("/today/{habitId}")
  public CheckinDto createTodayCheckin(@PathVariable Long habitId) {
    return createTodayCheckin.createTodayCheckin(habitId);
  }

  @Operation(
      summary = "Delete all check-ins for a habit",
      description = "Delete all check-ins associated with a specific habit ID"
  )
  @DeleteMapping("/habit/{habitId}")
  public ResponseEntity<Void> deleteAllCheckinsForHabit(@PathVariable Long habitId) {
    throw new UnsupportedOperationException("Not implemented yet");
  }
}
