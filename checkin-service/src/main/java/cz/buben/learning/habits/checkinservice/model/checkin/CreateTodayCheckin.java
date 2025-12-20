package cz.buben.learning.habits.checkinservice.model.checkin;

import cz.buben.learning.habits.checkinservice.UserIdProvider;
import cz.buben.learning.habits.checkinservice.domain.Checkin;
import cz.buben.learning.habits.checkinservice.mapping.CheckinMapper;
import cz.buben.learning.habits.checkinservice.repository.CheckinRepository;
import cz.buben.learning.habits.common.dto.CheckinDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class CreateTodayCheckin {

  private static final Logger LOGGER = LoggerFactory.getLogger(CreateTodayCheckin.class);

  private static final String TOPIC = "checkin";

  private final CheckinRepository checkinRepository;
  private final UserIdProvider userIdProvider;
  private final CheckinMapper checkinMapper;
  private final KafkaTemplate<String, CheckinDto> kafkaTemplate;

  @Transactional
  public CheckinDto createTodayCheckin(Long habitId) {
    String userId = userIdProvider.getCurrentUserId().orElseThrow(
        () -> new RuntimeException("User not authenticated"));

    Checkin checkin = Checkin.builder()
        .habitId(habitId)
        .userId(userId)
        .day(LocalDate.now())
        .build();
    Checkin saved = checkinRepository.save(checkin);
    CheckinDto checkinDto = checkinMapper.entityToDto(saved);
    LOGGER.info("Sending checkin to Kafka topic: {}", checkinDto);
    kafkaTemplate.send(TOPIC, checkinDto);
    return checkinDto;
  }
}
