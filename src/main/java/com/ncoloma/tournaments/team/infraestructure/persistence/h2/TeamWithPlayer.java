package com.ncoloma.tournaments.team.infraestructure.persistence.h2;

import java.util.UUID;

public interface TeamWithPlayer {
  UUID getId();
  String getName();
  double getFunds();
  PlayerEntity getPlayers();
}
