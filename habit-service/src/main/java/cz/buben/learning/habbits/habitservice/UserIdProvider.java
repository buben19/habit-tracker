package cz.buben.learning.habbits.habitservice;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserIdProvider {

  public Optional<String> getCurrentUserId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null) {
      String userId = authentication.getName();
      return Optional.ofNullable(userId);
    } else {
      return Optional.empty();
    }
  }
}
