package cz.buben.learning.habits.habitservice.model.habits

import cz.buben.learning.habits.common.dto.HabitDto
import cz.buben.learning.habits.habitservice.domain.Habit
import cz.buben.learning.habits.habitservice.mapping.HabitMapper
import cz.buben.learning.habits.habitservice.repository.HabitRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class UpdateHabitTest extends Specification {

  @Autowired
  HabitMapper habitMapper;
  HabitRepository habitRepository = Mock()
  UpdateHabit updateHabit

  void setup() {
    updateHabit = new UpdateHabit(
        habitRepository,
        habitMapper
    )
  }

  def "context loads"() {
    expect:
    habitMapper
  }

  def "update habit"() {
    given:
    def updateDto = HabitDto.builder()
        .id(1L)
        .name("Updated Habit Name")
        .description("Updated Description")
        .build()

    when:
    def habitDto = updateHabit.update(updateDto)

    then:
    1 * habitRepository.save(_ as Habit) >> { args ->
      def habit = args[0] as Habit
      assert habit.id == 1L
      assert habit.name == "Updated Habit Name"
      assert habit.description == "Updated Description"
      return Habit.builder()
          .id(habit.id)
          .name(habit.name)
          .description(habit.description)
          .build()
    }

    expect:
    habitDto
    habitDto == HabitDto.builder()
        .id(1L)
        .name("Updated Habit Name")
        .description("Updated Description")
        .build()
  }
}
