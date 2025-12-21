package cz.buben.learning.habits.habitservice.client;

import cz.buben.learning.habits.common.dto.CheckinDto;
import cz.buben.learning.habits.common.dto.DeleteCheckinDtoOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("checkin-service")
public interface CheckinClient {

  Logger LOGGER = LoggerFactory.getLogger(CheckinClient.class);

  @Cacheable(value = "checkinCache", key = "#habitId")
  @GetMapping("/api/checkins/habit/{habitId}")
  List<CheckinDto> getCheckinsByHabitId(@PathVariable Long habitId);

  @CacheEvict(value = "checkinCache", key = "#habitId")
  default void clearCacheByHabitId(Long habitId) {
    LOGGER.info("Clearing cache for Habit id: {}", habitId);
  }

  @DeleteMapping("/api/checkins/habit/{habitId}")
  DeleteCheckinDtoOut deleteCheckinsByHabitId(@PathVariable Long habitId);
}
