package cz.buben.learning.habbits.habitservice.client;

import cz.buben.learning.habbits.habitservice.dto.CheckinDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("checkin-service")
public interface CheckinClient {

  // TODO: Solve authentication between services
  @GetMapping("/api/checkins/habit/{habitId}")
  List<CheckinDto> getCheckinsByHabitId(@PathVariable Long habitId);
}
