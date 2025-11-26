package cz.buben.learning.habits.checkinservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "checkins")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Checkin {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long habitId;

  @Column(nullable = false)
  private String userId;

  @Column(nullable = false)
  private LocalDate day;
}
