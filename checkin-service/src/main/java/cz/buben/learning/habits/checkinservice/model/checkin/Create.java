package cz.buben.learning.habits.checkinservice.model.checkin;

import cz.buben.learning.habits.checkinservice.domain.Checkin;
import cz.buben.learning.habits.checkinservice.dto.CheckinDtoIn;
import cz.buben.learning.habits.checkinservice.mapping.CheckinDtoInMapper;
import cz.buben.learning.habits.checkinservice.mapping.CheckinMapper;
import cz.buben.learning.habits.checkinservice.repository.CheckinRepository;
import cz.buben.learning.habits.common.dto.CheckinDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Create {

  private static final Logger LOGGER = LoggerFactory.getLogger(Create.class);

  private static final String TOPIC = "checkin";

  private final CheckinRepository checkinRepository;
  private final CheckinDtoInMapper checkinDtoInMapper;
  private final CheckinMapper checkinMapper;
  private final KafkaTemplate<String, CheckinDto> kafkaTemplate;

  @Transactional
  public CheckinDto createCheckin(CheckinDtoIn checkinDtoIn) {
    Checkin checkin = checkinDtoInMapper.dtoToEntity(checkinDtoIn);
    Checkin saved = checkinRepository.save(checkin);
    CheckinDto dtoOut = checkinMapper.entityToDto(saved);
    LOGGER.info("Sending checkin to Kafka topic: {}", dtoOut);
    kafkaTemplate.send(TOPIC, dtoOut);
    return dtoOut;
  }
}
