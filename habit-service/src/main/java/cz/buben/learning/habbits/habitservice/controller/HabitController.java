package cz.buben.learning.habbits.habitservice.controller;

import cz.buben.learning.habbits.habitservice.domain.Habit;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
@AllArgsConstructor
public class HabitController {

  @GetMapping
  public List<Habit> getHabits() {
  }

  @GetMapping("/{id}")
  public Habit getHabit(@RequestParam Long id) {
  }

  @PostMapping
  public Habit createHabit(@RequestBody Habit habit) {
  }

  @PutMapping("/{id}")
  public ResponseEntity<Habit> updateHabit(@RequestBody Habit habit) {
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteHabit(@PathVariable Long id) {
  }
}
