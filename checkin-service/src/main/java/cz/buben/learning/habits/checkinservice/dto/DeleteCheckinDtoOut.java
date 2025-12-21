package cz.buben.learning.habits.checkinservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Response DTO for deleted check-ins")
public class DeleteCheckinDtoOut {

  @Schema(
      description = "Number of deleted check-ins",
      example = "3"
  )
  private Integer deleted;
}
