package cz.buben.learning.habits.checkinservice

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

@SpringBootTest(properties = [
  'spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}'
])
@Import(KafkaTestConfig)
@DirtiesContext
@EmbeddedKafka(partitions = 1, topics = ["checkin"])
class KafkaTest extends Specification {

  @Autowired
  KafkaConsumer consumer

  @Autowired
  KafkaTemplate<String, CheckinDto> kafkaTemplate

  def setup() {
    consumer.subscribe(["checkin"])
  }

  def cleanup() {
    consumer.unsubscribe()
  }

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
    kafkaTemplate.send("checkin", checkinDto)

    then:
    def singleRecord = KafkaTestUtils.getSingleRecord(consumer, "checkin")

    expect:
    singleRecord.value() as CheckinDto == checkinDto
  }
}
