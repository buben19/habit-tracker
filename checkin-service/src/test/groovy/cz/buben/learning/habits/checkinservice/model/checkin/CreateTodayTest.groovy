package cz.buben.learning.habits.checkinservice.model.checkin

import cz.buben.learning.habits.checkinservice.UserIdProvider
import cz.buben.learning.habits.checkinservice.domain.Checkin
import cz.buben.learning.habits.checkinservice.mapping.CheckinMapper
import cz.buben.learning.habits.checkinservice.repository.CheckinRepository
import cz.buben.learning.habits.common.dto.CheckinDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.core.KafkaTemplate
import spock.lang.Specification

@SpringBootTest
class CreateTodayTest extends Specification {

  @Autowired
  CheckinMapper checkinMapper
  CheckinRepository checkinRepository = Mock()
  UserIdProvider userIdProvider = Mock()
  KafkaTemplate<String, CheckinDto> kafkaTemplate = Mock()
  CreateToday createTodayCheckin

  def setup() {
    createTodayCheckin = new CreateToday(
        checkinRepository,
        userIdProvider,
        checkinMapper,
        kafkaTemplate
    )
  }

  def "context loads"() {
    expect:
    checkinMapper
  }

  def "create today checkin"() {
    given:
    def habitId = 1L

    when:
    def checkinDto = createTodayCheckin.createTodayCheckin(habitId)

    then:
    1 * userIdProvider.getCurrentUserId() >> Optional.of("test-user-id")

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
