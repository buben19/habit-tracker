package cz.buben.learning.habits.checkinservice.model.checkin

import cz.buben.learning.habits.checkinservice.domain.Checkin
import cz.buben.learning.habits.checkinservice.mapping.CheckinMapper
import cz.buben.learning.habits.checkinservice.repository.CheckinRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class FindCheckinsByHabitIdTest extends Specification {

  @Autowired
  CheckinMapper checkinMapper
  CheckinRepository checkinRepository = Mock()
  FindCheckinsByHabitId findCheckinsByByHabitId

  def setup() {
    findCheckinsByByHabitId = new FindCheckinsByHabitId(
        checkinRepository,
        checkinMapper
    )
  }

  def "context loads"() {
    expect:
    checkinMapper
  }

  def "find checkins by habit id"() {
    given:
    def habitId = 1L

    when:
    def byHabitId = findCheckinsByByHabitId.findCheckinsByHabitId(habitId)

    then:
    1 * checkinRepository.findByHabitId(habitId) >> [
        Checkin.builder()
            .id(1L)
            .habitId(habitId)
            .userId("test-user-id")
            .build(),
        Checkin.builder()
            .id(2L)
            .habitId(habitId)
            .userId("test-user-id")
            .build(),
        Checkin.builder()
            .id(3L)
            .habitId(habitId)
            .userId("test-user-id")
            .build(),
    ]

    expect:
    byHabitId
    byHabitId.size() == 3
  }
}
