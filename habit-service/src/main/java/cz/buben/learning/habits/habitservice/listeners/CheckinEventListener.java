package cz.buben.learning.habits.habitservice.listeners;

import cz.buben.learning.habits.habitservice.client.CheckinClient;
import cz.buben.learning.habits.common.dto.CheckinDto;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Profile("!test")
public class CheckinEventListener {

  private final CheckinClient checkinClient;

  @KafkaListener(
      topics = "checkin",
      groupId = "checkin"
  )
  public void onCheckinEvent(CheckinDto checkinDto) {
    checkinClient.clearCacheByHabitId(checkinDto.getHabitId());
  }
}
