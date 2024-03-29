package com.ncoloma.tournaments.team.domain.team.player;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface PlayerRepository {
  void save(Player player);
  Set<Optional<Player>> find();
  Optional<Player> findOne(UUID id);
  UUID generateID();
}
