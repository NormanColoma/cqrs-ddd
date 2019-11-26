package com.ncoloma.players.domain;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface PlayerRepository {
  void save(Player player);
  Set<Optional<Player>> find();
  Optional<Player> findOne(UUID id);
}
