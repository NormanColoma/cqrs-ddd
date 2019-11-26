package com.ncoloma.players.infraestructure.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;
import java.util.UUID;

@Entity
@DynamicUpdate
@Table(name = "Team")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeamEntity {
    @Id
    private UUID id;
    private String name;
    private double funds;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
    private Set<PlayerEntity> players;
}
