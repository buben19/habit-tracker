package cz.buben.learning.habits.habitservice.client;

import cz.buben.learning.habits.common.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "keycloak", url = "${application.configuration.keycloak.url}")
public interface UserClient {

  @GetMapping("/admin/realms/${application.configuration.keycloak.realm}/users/{userId}")
  UserDto findUserById(@PathVariable String userId);
}
