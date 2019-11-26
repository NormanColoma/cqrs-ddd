package com.ncoloma.players.infraestructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TeamJPARepository extends JpaRepository<TeamEntity, UUID> {
}
