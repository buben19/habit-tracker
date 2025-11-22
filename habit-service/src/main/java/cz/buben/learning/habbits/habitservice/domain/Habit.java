package cz.buben.learning.habbits.habitservice.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "habits")
@Data
public class Habit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String userId;

  @Column(nullable = false)
  private String name;
  private String description;
  private String schedule;
}
