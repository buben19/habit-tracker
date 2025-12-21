package cz.buben.learning.habits.checkinservice.model.checkin;

import cz.buben.learning.habits.common.dto.DeleteCheckinDtoOut;
import cz.buben.learning.habits.checkinservice.repository.CheckinRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteAllForHabit {

  private final CheckinRepository checkinRepository;

  @Transactional
  public DeleteCheckinDtoOut deleteAllForHabit(Long habitId) {
    int deleted = checkinRepository.deleteByHabitId(habitId);

    return DeleteCheckinDtoOut.builder()
        .deleted(deleted)
        .build();
  }
}
