package cz.buben.learning.habits.habitservice.model.habits

import cz.buben.learning.habits.common.dto.HabitDto
import cz.buben.learning.habits.habitservice.domain.Habit
import cz.buben.learning.habits.habitservice.domain.Schedule
import cz.buben.learning.habits.habitservice.dto.UpdateHabitDtoIn
import cz.buben.learning.habits.habitservice.mapping.HabitMapper
import cz.buben.learning.habits.habitservice.repository.HabitRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class UpdateHabitTest extends Specification {

  @Autowired
  HabitMapper habitMapper
  HabitRepository habitRepository = Mock()
  UpdateHabit updateHabit

  void setup() {
    updateHabit = new UpdateHabit(
        habitRepository,
        habitMapper
    )
  }

  def "update habit"() {
    given:
    def updateHabitDtoIn = UpdateHabitDtoIn.builder()
        .name("Updated Habit Name")
        .description("Updated Description")
        .schedule(Schedule.DAILY)
        .build()

    when:
    def habitDto = updateHabit.update(1L, updateHabitDtoIn)

    then:
    1 * habitRepository.findById(1L) >> Optional.of(
        Habit.builder()
            .id(1L)
            .name("Old Habit Name")
            .description("Old Description")
            .schedule(Schedule.DAILY)
            .build()
    )

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
