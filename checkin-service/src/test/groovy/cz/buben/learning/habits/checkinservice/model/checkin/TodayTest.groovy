package cz.buben.learning.habits.checkinservice.model.checkin

import cz.buben.learning.habits.checkinservice.domain.Checkin
import cz.buben.learning.habits.checkinservice.mapping.CheckinMapper
import cz.buben.learning.habits.checkinservice.repository.CheckinRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import java.time.LocalDate

@SpringBootTest
class TodayTest extends Specification {

  @Autowired
  CheckinMapper checkinMapper
  CheckinRepository checkinRepository = Mock()
  Today today

  def setup() {
    today = new Today(
        checkinRepository,
        checkinMapper
    )
  }

  def "context loads"() {
    expect:
    checkinMapper
  }

  def "get today's checkins"() {
    given:
    def userId = "test-user-id"

    when:
    def todayCheckins = today.getTodayCheckins(userId)

    then:
    1 * checkinRepository.findByUserIdAndDay(userId, _ as LocalDate) >> [
        Checkin.builder()
            .id(1L)
            .habitId(1L)
            .userId("test-user-id")
            .build(),
        Checkin.builder()
            .id(2L)
            .habitId(2L)
            .userId("test-user-id")
            .build(),
        Checkin.builder()
            .id(3L)
            .habitId(3L)
            .userId("test-user-id")
            .build(),
    ]

    expect:
    todayCheckins
    todayCheckins.size() == 3
  }
}
