package cz.buben.learning.habits.habitservice.model.habit


import cz.buben.learning.habits.habitservice.domain.Habit
import cz.buben.learning.habits.habitservice.mapping.HabitMapper
import cz.buben.learning.habits.habitservice.repository.HabitRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class GetTest extends Specification {

  @Autowired
  HabitMapper habitMapper;
  HabitRepository habitRepository = Mock()
  Get getHabit

  void setup() {
    getHabit = new Get(
        habitRepository,
        habitMapper
    )
  }

  def "context loads"() {
    expect:
    habitMapper
  }

  def "get habit"() {
    given:
    def habitId = 1L

    when:
    def habitDto = getHabit.get(habitId)

    then:
    1 * habitRepository.findById(habitId) >> Optional.of(Habit.builder()
        .id(habitId)
        .name("Test Habit")
        .description("This is a test habit")
        .build())

    expect:
    habitDto
  }
}
