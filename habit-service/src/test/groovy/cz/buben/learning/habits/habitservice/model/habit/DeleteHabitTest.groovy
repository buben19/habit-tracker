package cz.buben.learning.habits.habitservice.model.habit

import cz.buben.learning.habits.habitservice.repository.HabitRepository
import spock.lang.Specification

class DeleteHabitTest extends Specification {

  HabitRepository habitRepository = Mock()
  DeleteHabit deleteHabit

  def setup() {
    deleteHabit = new DeleteHabit(
        habitRepository
    )
  }

  def "delete habit"() {
    given:
    def habitId = 1L

    when:
    deleteHabit.delete(habitId)

    then:
    1 * habitRepository.deleteById(habitId)
  }
}
