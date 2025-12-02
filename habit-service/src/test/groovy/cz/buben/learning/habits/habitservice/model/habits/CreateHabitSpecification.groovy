package cz.buben.learning.habits.habitservice.model.habits

import cz.buben.learning.habits.common.dto.HabitDto
import cz.buben.learning.habits.habitservice.UserIdProvider
import cz.buben.learning.habits.habitservice.domain.Habit
import cz.buben.learning.habits.habitservice.mapping.HabitMapper
import cz.buben.learning.habits.habitservice.repository.HabitRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class CreateHabitSpecification extends Specification {

    @Autowired
    HabitMapper habitMapper
    HabitRepository habitRepository = Mock()
    UserIdProvider userIdProvider = Mock()
    CreateHabit createHabit

    def setup() {
        createHabit = new CreateHabit(
                habitRepository,
                userIdProvider,
                habitMapper
        )
    }

    def "context loads"() {
        expect:
        habitMapper
    }

    def "create habit"() {
        given:
        def dto = HabitDto.builder()
                .name("Test Habit")
                .description("This is a test habit")
                .build()

        when:
        def create = createHabit.create(dto)

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
