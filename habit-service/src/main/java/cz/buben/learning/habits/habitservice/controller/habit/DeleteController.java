package cz.buben.learning.habits.habitservice.controller.habit;

import cz.buben.learning.habits.habitservice.model.habit.Delete;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/habits")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "habit")
@AllArgsConstructor
public class DeleteController {

  private final Delete delete;

  @Operation(
      summary = "Delete a habit",
      description = "Delete a habit by its ID",
      parameters = @Parameter(
          name = "id",
          in = ParameterIn.PATH,
          description = "ID of the habit to delete",
          required = true,
          example = "1"
      ),
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Successful operation"
          ),
          @ApiResponse(
              responseCode = "401",
              description = "Unauthorized"
          )
      }
  )
  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('client_user')")
  public ResponseEntity<Void> deleteHabit(@PathVariable Long id) {
    delete.delete(id);
    return ResponseEntity.noContent().build();
  }
}
