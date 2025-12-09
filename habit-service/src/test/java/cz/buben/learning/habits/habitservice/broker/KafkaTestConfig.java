package cz.buben.learning.habits.habitservice.broker;

import cz.buben.learning.habits.common.dto.CheckinDto;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JacksonJsonSerializer;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

@TestConfiguration
public class KafkaTestConfig {

  @Value(value = "${spring.kafka.bootstrap-servers}")
  private String bootstrapAddress;

  @Bean
  public ProducerFactory<String, CheckinDto> producerFactory() {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    configProps.put(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configProps.put(VALUE_SERIALIZER_CLASS_CONFIG, JacksonJsonSerializer.class);
    configProps.put(JacksonJsonSerializer.ADD_TYPE_INFO_HEADERS, true);
    return new DefaultKafkaProducerFactory<>(configProps);
  }

  @Bean
  public KafkaTemplate<String, CheckinDto> kafkaTemplate(ProducerFactory<String, CheckinDto> producerFactory) {
    return new KafkaTemplate<>(producerFactory);
  }

  @Bean
  public KafkaConsumer<String, CheckinDto> testConsumer(ConsumerFactory<String, CheckinDto> consumerFactory) {

    // Use the factory; it already has correct bootstrap.servers
    return (KafkaConsumer<String, CheckinDto>) consumerFactory.createConsumer();
  }
}
