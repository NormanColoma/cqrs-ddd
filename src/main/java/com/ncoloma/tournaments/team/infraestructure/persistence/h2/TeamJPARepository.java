package com.ncoloma.tournaments.team.infraestructure.persistence.h2;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TeamJPARepository extends JpaRepository<TeamEntity, UUID> {
}
