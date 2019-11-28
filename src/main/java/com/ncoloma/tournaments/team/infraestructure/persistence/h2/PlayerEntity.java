package com.ncoloma.tournaments.team.infraestructure.persistence.h2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "PLAYER")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@DynamicUpdate
class PlayerEntity {
  @Id
  @Type(type = "uuid-char")
  private UUID id;
  private String name;
  private int dorsal;
  private double price;
  @ManyToOne(fetch = FetchType.LAZY)
  private TeamEntity team;
}
