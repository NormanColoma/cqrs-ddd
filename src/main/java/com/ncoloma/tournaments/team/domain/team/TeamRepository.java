package com.ncoloma.tournaments.team.domain.team;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeamRepository {
    void save(Team team);
    Optional<Team> findOne(UUID id);
    Optional<Team> findOneWithPlayer(UUID playerId);
    List<Team> findAll();
    UUID generateID();
}
