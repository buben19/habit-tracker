package cz.buben.learning.habits.habitservice.broker

import cz.buben.learning.habits.common.dto.CheckinDto
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.kafka.test.utils.KafkaTestUtils
import org.springframework.test.annotation.DirtiesContext
import spock.lang.Specification

@SpringBootTest
@DirtiesContext
@Import(KafkaTestConfig)
@EmbeddedKafka(partitions = 1, topics = ["checkin"])
class KafkaTest extends Specification {

  @Autowired
  private KafkaConsumer consumer;

  @Autowired
  KafkaTemplate<String, CheckinDto> kafkaTemplate

  def "context loads"() {
    expect:
    true
  }

  def "sending message to kafka"() {
    given:
    def checkinDto = CheckinDto.builder()
        .id(1L)
        .habitId(1L)
        .userId("test-user-id")
        .build()

    when:
    consumer.subscribe(["checkin"])
    kafkaTemplate.send("checkin", checkinDto)

    then:
    def singleRecord = KafkaTestUtils.getSingleRecord(consumer, "checkin")

    expect:
    singleRecord.value() as CheckinDto == checkinDto
  }
}