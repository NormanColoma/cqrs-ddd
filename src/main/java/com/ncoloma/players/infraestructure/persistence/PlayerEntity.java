package com.ncoloma.players.infraestructure.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "Player")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@DynamicUpdate
class PlayerEntity {
  @Id
  private UUID id;
  private String name;
  private int dorsal;
}
