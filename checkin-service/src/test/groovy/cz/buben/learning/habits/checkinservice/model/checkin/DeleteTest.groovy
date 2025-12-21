package cz.buben.learning.habits.checkinservice.model.checkin

import cz.buben.learning.habits.checkinservice.repository.CheckinRepository
import spock.lang.Specification

class DeleteTest extends Specification {

  Delete deleteCheckin
  CheckinRepository checkinRepository = Mock()

  def setup() {
    deleteCheckin = new Delete(
        checkinRepository
    )
  }

  def "delete checkin"() {
    given:
    def checkinId = 1L

    when:
    deleteCheckin.delete(checkinId)

    then:
    1 * checkinRepository.deleteById(checkinId)
  }
}
