package cz.buben.learning.habits.habitservice.model.habit

import cz.buben.learning.habits.habitservice.UserIdProvider
import cz.buben.learning.habits.habitservice.domain.Habit
import cz.buben.learning.habits.habitservice.domain.Schedule
import cz.buben.learning.habits.habitservice.dto.CreateHabitDtoIn
import cz.buben.learning.habits.habitservice.mapping.CreateHabitMapper
import cz.buben.learning.habits.habitservice.mapping.HabitMapper
import cz.buben.learning.habits.habitservice.repository.HabitRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class CreateHabitTest extends Specification {

  @Autowired
  HabitMapper habitMapper
  @Autowired
  CreateHabitMapper createHabitMapper
  HabitRepository habitRepository = Mock()
  UserIdProvider userIdProvider = Mock()
  CreateHabit createHabit

  def setup() {
    createHabit = new CreateHabit(
        habitRepository,
        userIdProvider,
        createHabitMapper,
        habitMapper
    )
  }

  def "create habit"() {
    given:
    def createHabitDtoIn = CreateHabitDtoIn.builder()
        .name("Test Habit")
        .description("This is a test habit")
        .schedule(Schedule.DAILY)
        .build()

    when:
    def create = createHabit.create(createHabitDtoIn)

    then:
    1 * userIdProvider.getCurrentUserId() >> Optional.of("test-user-id")

    then:
    1 * habitRepository.save(_ as Habit) >> { args ->
      def habit = args[0] as Habit
      assert habit.name == "Test Habit"
      assert habit.description == "This is a test habit"
      assert habit.userId == "test-user-id"
      return Habit.builder()
          .id(1L)
          .name(habit.name)
          .description(habit.description)
          .userId(habit.userId)
          .build()
    }

    expect:
    create
  }

}
