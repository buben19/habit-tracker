package cz.buben.learning.habbits.habitservice.controller;

import cz.buben.learning.habbits.habitservice.domain.Habit;
import cz.buben.learning.habbits.habitservice.model.habits.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
@AllArgsConstructor
public class HabitController {

  private final GetHabits getHabits;
  private final GetHabit getHabit;
  private final CreateHabit createHabit;
  private final UpdateHabit updateHabit;
  private final DeleteHabit deleteHabit;

  @GetMapping
  public List<Habit> getHabits() {
    return getHabits.all();
  }

  @GetMapping("/{id}")
  public Habit getHabit(@RequestParam Long id) {
    return getHabit.get(id);
  }

  @PostMapping
  public Habit createHabit(@RequestBody Habit habit) {
    return createHabit.create(habit);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Habit> updateHabit(@RequestBody Habit habit) {
    Habit updatedHabit = updateHabit.update(habit);
    return ResponseEntity.ok(updatedHabit);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteHabit(@PathVariable Long id) {
    deleteHabit.delete(id);
    return ResponseEntity.noContent().build();
  }
}
