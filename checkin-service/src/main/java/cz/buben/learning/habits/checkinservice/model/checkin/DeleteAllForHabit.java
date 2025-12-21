package cz.buben.learning.habits.checkinservice.model.checkin;

import cz.buben.learning.habits.checkinservice.dto.DeleteCheckinDtoOut;
import cz.buben.learning.habits.checkinservice.repository.CheckinRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteAllForHabit {

  private final CheckinRepository checkinRepository;

  public DeleteCheckinDtoOut deleteAllForHabit(Long habitId) {
    int deleted = checkinRepository.deleteByHabitId(habitId);

    return DeleteCheckinDtoOut.builder()
        .deleted(deleted)
        .build();
  }
}
