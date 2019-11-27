package com.ncoloma.tournaments.team.infraestructure.persistence.h2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlayerJPARepository extends JpaRepository<PlayerEntity, UUID> {

}
