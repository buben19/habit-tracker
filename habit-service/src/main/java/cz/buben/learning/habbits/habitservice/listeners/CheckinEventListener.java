package cz.buben.learning.habbits.habitservice.listeners;

import cz.buben.learning.habbits.habitservice.client.CheckinClient;
import cz.buben.learning.habits.common.dto.CheckinDto;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CheckinEventListener {

  private final CheckinClient checkinClient;

  @KafkaListener(
      topics = "checkin",
      groupId = "checkin",
      containerFactory = "checkinKafkaListenerContainerFactory"
  )
  public void onCheckinEvent(CheckinDto checkinDto) {
    checkinClient.clearCacheByHabitId(checkinDto.getHabitId());
  }
}
