package cz.buben.learning.habits.habitservice;

import cz.buben.learning.habits.habitservice.security.converter.JwtAuthConverter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {

  private final JwtAuthConverter jwtAuthConverter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) {
    http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(
                "/actuator/**",
                "/habit-service/v3/api-docs/**",
                "/habit-service/swagger-ui/**",
                "/habit-service/swagger-ui.html"
            ).permitAll()
            .anyRequest().authenticated())
        .oauth2ResourceServer(oauth2 -> oauth2
            .jwt(jwtCustomizer -> {
              jwtCustomizer.jwtAuthenticationConverter(jwtAuthConverter);
            }))
        .sessionManagement(securitySessionManagementConfigurer -> securitySessionManagementConfigurer
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    return http.build();
  }
}
