package cz.buben.learning.habits.common.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(GlobalExceptionHandler.class)
public class CommonErrorHandlingAutoConfiguration {
}
