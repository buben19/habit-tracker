package cz.buben.learning.habbits.habitservice.checkin;

import cz.buben.learning.habbits.habitservice.dto.CheckinDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
    name = "checkin-service",
    url = "${application.configuration.checkin-service.url}"
)
public interface CheckinClient {

  @GetMapping("/checkins/habit/{habitId}")
  List<CheckinDto> getCheckinsByHabitId(@PathVariable Long habitId);
}
