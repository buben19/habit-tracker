package cz.buben.learning.habits.habitservice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorResponse {

  private Instant timestamp = Instant.now();
  private int status;
  private String error;          // e.g. "Bad Request"
  private List<FieldError> fieldErrors;

  @Data
  @Builder
  public static class FieldError {
    private String field;
    private String message;

    public FieldError(String field, String message) {
      this.field = field;
      this.message = message;
    }
  }
}
