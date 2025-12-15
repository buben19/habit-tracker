package cz.buben.learning.habits.checkinservice;

import cz.buben.learning.habits.common.dto.CheckinDto;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.jspecify.annotations.NullMarked;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.ConsumerFactory;

@TestConfiguration
public class KafkaTestConfig {

  @Bean
  @NullMarked
  public KafkaConsumer<String, CheckinDto> testConsumer(ConsumerFactory<String, CheckinDto> consumerFactory) {
    return (KafkaConsumer<String, CheckinDto>) consumerFactory.createConsumer();
  }
}
