package cz.buben.learning.habbits.checkinservice.controller;

import cz.buben.learning.habbits.checkinservice.domain.Checkin;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checkins")
public class CheckinController {

  @GetMapping
  public List<Checkin> getAllCheckins() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @PostMapping
  public Checkin create(@RequestBody Checkin checkin) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @GetMapping("/today")
  public List<Checkin> today(@RequestParam Long userId) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
