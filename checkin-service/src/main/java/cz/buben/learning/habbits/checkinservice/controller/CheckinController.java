package cz.buben.learning.habbits.checkinservice.controller;

import cz.buben.learning.habbits.checkinservice.domain.Checkin;
import cz.buben.learning.habbits.checkinservice.model.Create;
import cz.buben.learning.habbits.checkinservice.model.GetAll;
import cz.buben.learning.habbits.checkinservice.model.Today;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checkins")
@AllArgsConstructor
public class CheckinController {

  private final GetAll getAll;
  private final Create create;
  private final Today today;

  @GetMapping
  public List<Checkin> getAllCheckins() {
    return getAll.getAllCheckins();
  }

  @PostMapping
  public Checkin create(@RequestBody Checkin checkin) {
    return create.createCheckin(checkin);
  }

  @GetMapping("/today")
  public List<Checkin> today(@RequestParam Long userId) {
    return today.getTodayCheckins(userId);
  }
}
