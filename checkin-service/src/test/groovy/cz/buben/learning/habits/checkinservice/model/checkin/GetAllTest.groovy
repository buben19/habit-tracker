package cz.buben.learning.habits.checkinservice.model.checkin

import cz.buben.learning.habits.checkinservice.domain.Checkin
import cz.buben.learning.habits.checkinservice.mapping.CheckinMapper
import cz.buben.learning.habits.checkinservice.repository.CheckinRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class GetAllTest extends Specification {

  @Autowired
  CheckinMapper checkinMapper
  CheckinRepository checkinRepository = Mock()
  GetAll getAll

  def setup() {
    getAll = new GetAll(
        checkinRepository,
        checkinMapper
    )
  }

  def "context loads"() {
    expect:
    checkinMapper
  }

  def "get all checkins"() {
    when:
    def allCheckins = getAll.getAllCheckins()

    then:
    1 * checkinRepository.findAll() >> [
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
    allCheckins
    allCheckins.size() == 3
  }
}
