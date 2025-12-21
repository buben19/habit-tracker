package cz.buben.learning.habits.checkinservice.model.checkin

import cz.buben.learning.habits.checkinservice.domain.Checkin
import cz.buben.learning.habits.checkinservice.dto.CheckinDtoIn
import cz.buben.learning.habits.checkinservice.mapping.CheckinDtoInMapper
import cz.buben.learning.habits.checkinservice.mapping.CheckinMapper
import cz.buben.learning.habits.checkinservice.repository.CheckinRepository
import cz.buben.learning.habits.common.dto.CheckinDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.core.KafkaTemplate
import spock.lang.Specification

@SpringBootTest
class CreateTest extends Specification {

  @Autowired
  CheckinMapper checkinMapper
  @Autowired
  CheckinDtoInMapper checkinDtoInMapper
  CheckinRepository checkinRepository = Mock()
  KafkaTemplate<String, CheckinDto> kafkaTemplate = Mock()
  Create create

  def setup() {
    create = new Create(
        checkinRepository,
        checkinDtoInMapper,
        checkinMapper,
        kafkaTemplate
    )
  }

  def "creates checkin and sends event"() {
    given:
    def checkinDtoIn = CheckinDtoIn.builder()
        .habitId(1L)
        .userId("test-user-id")
        .build()

    when:
    def checkinDto = create.createCheckin(checkinDtoIn)

    then:
    1 * checkinRepository.save(_ as Checkin) >> { args ->
      def checkin = args[0] as Checkin
      assert checkin.habitId == 1L
      assert checkin.userId == "test-user-id"
      return Checkin.builder()
          .id(1L)
          .habitId(checkin.habitId)
          .userId(checkin.userId)
          .build()
    }

    then:
    1 * kafkaTemplate.send("checkin", _ as CheckinDto)

    expect:
    checkinDto
  }
}
