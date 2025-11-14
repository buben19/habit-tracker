package cz.buben.learning.habbits.checkinservice.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "checkins")
@Data
public class Checkin {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long habitId;
  private Long userId;
  private LocalDate day;
}
