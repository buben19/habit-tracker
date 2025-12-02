package cz.buben.learning.habits.habitservice.model.habits

import cz.buben.learning.habits.habitservice.UserIdProvider
import cz.buben.learning.habits.habitservice.domain.Habit
import cz.buben.learning.habits.habitservice.mapping.HabitMapper
import cz.buben.learning.habits.habitservice.repository.HabitRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class GetAllHabitsSpecification extends Specification {

  @Autowired
  HabitMapper habitMapper;
  UserIdProvider userIdProvider = Mock()
  HabitRepository habitRepository = Mock()
  GetAllHabits getAllHabits

  void setup() {
    getAllHabits = new GetAllHabits(
        habitRepository,
        userIdProvider,
        habitMapper
    )
  }

  def "context loads"() {
    expect:
    habitMapper
  }

  def "get all habits"() {
    when:
    def habits = getAllHabits.all()

    then:
    1 * userIdProvider.getCurrentUserId() >> Optional.of("test-user-id")
    1 * habitRepository.findByUserId("test-user-id") >> [
        Habit.builder()
            .id(1L)
            .name("Habit 1")
            .description("Description 1")
            .userId("test-user-id")
            .build(),
        Habit.builder()
            .id(2L)
            .name("Habit 2")
            .description("Description 2")
            .userId("test-user-id")
            .build(),
        Habit.builder()
            .id(3L)
            .name("Habit 3")
            .description("Description 3")
            .userId("test-user-id")
            .build()
    ]

    expect:
    habits.size() == 3
  }
}
