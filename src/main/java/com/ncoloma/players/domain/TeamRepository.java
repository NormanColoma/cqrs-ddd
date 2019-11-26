package com.ncoloma.players.domain;

import java.util.Optional;
import java.util.UUID;

public interface TeamRepository {
    void save(Team team);
    Optional<Team> findOne(UUID id);
}
