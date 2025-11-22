package cz.buben.learning.habbits.checkinservice.controller;

import cz.buben.learning.habbits.checkinservice.domain.Checkin;
import cz.buben.learning.habbits.checkinservice.model.*;
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
  public List<Checkin> getAllCheckins() {
    return getAll.getAllCheckins();
  }

  @GetMapping("/habit/{habitId}")
  public List<Checkin> findCheckinsByByHabitId(@PathVariable Long habitId) {
    return findCheckinsByByHabitId.findCheckinsByHabitId(habitId);
  }

  @PostMapping
  public Checkin create(@RequestBody Checkin checkin) {
    return create.createCheckin(checkin);
  }

  @GetMapping("/today")
  public List<Checkin> today(@RequestParam String userId) {
    return today.getTodayCheckins(userId);
  }

  @PostMapping("/today/{habitId}")
  public Checkin createTodayCheckin(@PathVariable Long habitId) {
    return createTodayCheckin.createTodayCheckin(habitId);
  }
}
