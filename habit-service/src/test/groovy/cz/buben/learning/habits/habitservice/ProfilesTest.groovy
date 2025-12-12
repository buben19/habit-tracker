package cz.buben.learning.habits.habitservice

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class ProfilesTest extends Specification {

  @Value('${spring.profiles.active}')
  String activeProfile;

  def "test profiles"() {
    expect:
    activeProfile == "test"
  }
}
