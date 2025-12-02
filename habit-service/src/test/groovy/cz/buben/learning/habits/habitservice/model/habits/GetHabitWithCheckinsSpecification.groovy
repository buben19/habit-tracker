package cz.buben.learning.habits.habitservice.model.habits

import cz.buben.learning.habits.common.dto.CheckinDto
import cz.buben.learning.habits.common.dto.UserDto
import cz.buben.learning.habits.habitservice.UserIdProvider
import cz.buben.learning.habits.habitservice.client.CheckinClient
import cz.buben.learning.habits.habitservice.client.UserClient
import cz.buben.learning.habits.habitservice.domain.Habit
import cz.buben.learning.habits.habitservice.mapping.HabitMapper
import cz.buben.learning.habits.habitservice.repository.HabitRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import java.time.LocalDate

@SpringBootTest
class GetHabitWithCheckinsSpecification extends Specification {

  @Autowired
  HabitMapper habitMapper
  HabitRepository habitRepository = Mock()
  CheckinClient checkinClient = Mock()
  UserClient userClient = Mock()
  UserIdProvider userIdProvider = Mock()
  GetHabitWithCheckins getHabitWithCheckins

  def setup() {
    getHabitWithCheckins = new GetHabitWithCheckins(
        habitRepository,
        checkinClient,
        userClient,
        userIdProvider,
        habitMapper
    )
  }

  def "context loads"() {
    expect:
    habitMapper
  }

  def "get habits with check-ins"() {
    when:
    def habitsWithCheckins = getHabitWithCheckins.getHabitsWithCheckins()

    then:
    1 * userIdProvider.getCurrentUserId() >> Optional.of("test-user-id")
    1 * userClient.findUserById("test-user-id") >> UserDto.builder()
        .id("test-user-id")
        .firstName("first")
        .lastName("last")
        .email("first.last@example.com")
        .build()

    // Get habit for user
    1 * habitRepository.findByUserId("test-user-id") >> [
        Habit.builder()
          .id(1L)
          .name("First Habit")
          .description("This is a first habit")
          .userId("test-user-id")
          .build(),
        Habit.builder()
          .id(2L)
          .name("Second Habit")
          .description("This is second habit")
          .userId("test-user-id")
          .build(),
        Habit.builder()
          .id(3L)
          .name("Third Habit")
          .description("This is the third habit")
          .userId("test-user-id")
          .build()
    ]

    // Get check-ins for each habit
    1 * checkinClient.getCheckinsByHabitId(1L) >> [
        CheckinDto.builder()
            .id(1L)
            .habitId(1L)
            .day(LocalDate.EPOCH)
            .build(),
    ]
    1 * checkinClient.getCheckinsByHabitId(2L) >> []
    1 * checkinClient.getCheckinsByHabitId(3L) >> []

    // TODO: More comprehensive assertions
    // TODO: Test parallel fetching of check-ins?
    expect:
    habitsWithCheckins
  }
}
