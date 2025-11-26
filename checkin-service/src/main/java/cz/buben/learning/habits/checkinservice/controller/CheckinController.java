package cz.buben.learning.habits.checkinservice.controller;

import cz.buben.learning.habits.checkinservice.model.*;
import cz.buben.learning.habits.common.dto.CheckinDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checkins")
@AllArgsConstructor
public class CheckinController {

  private final GetAll getAll;
  private final Create create;
  private final FindCheckinsByByHabitId findCheckinsByByHabitId;
  private final Today today;
  private final CreateTodayCheckin createTodayCheckin;

  @GetMapping
  public List<CheckinDto> getAllCheckins() {
    return getAll.getAllCheckins();
  }

  @GetMapping("/habit/{habitId}")
  public List<CheckinDto> findCheckinsByByHabitId(@PathVariable Long habitId) {
    return findCheckinsByByHabitId.findCheckinsByHabitId(habitId);
  }

  @PostMapping
  public CheckinDto create(@Valid @RequestBody CheckinDto checkin) {
    return create.createCheckin(checkin);
  }

  @GetMapping("/today")
  public List<CheckinDto> today(@RequestParam String userId) {
    return today.getTodayCheckins(userId);
  }

  @PostMapping("/today/{habitId}")
  public CheckinDto createTodayCheckin(@PathVariable Long habitId) {
    return createTodayCheckin.createTodayCheckin(habitId);
  }
}
