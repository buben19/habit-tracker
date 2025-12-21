package cz.buben.learning.habits.habitservice.model.habit;

import cz.buben.learning.habits.common.dto.DeleteCheckinDtoOut;
import cz.buben.learning.habits.habitservice.client.CheckinClient;
import cz.buben.learning.habits.habitservice.dto.DeleteHabitWithCheckinsDtoOut;
import cz.buben.learning.habits.habitservice.repository.HabitRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteWithCheckins {

  private final HabitRepository habitRepository;
  private final CheckinClient checkinClient;

  @Transactional
  public DeleteHabitWithCheckinsDtoOut deleteWithCheckins(Long habitId) {
    habitRepository.findById(habitId).orElseThrow(
        () -> new IllegalArgumentException("Habit with id " + habitId + " not found"));

    DeleteCheckinDtoOut deleteCheckinDtoOut = checkinClient.deleteCheckinsByHabitId(habitId);
    habitRepository.deleteById(habitId);

    return DeleteHabitWithCheckinsDtoOut.builder()
        .deletedCheckins(deleteCheckinDtoOut.getDeleted())
        .build();
  }
}
