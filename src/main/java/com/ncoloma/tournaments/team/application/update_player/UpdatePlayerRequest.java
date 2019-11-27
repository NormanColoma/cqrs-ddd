package com.ncoloma.tournaments.team.application.update_player;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class UpdatePlayerRequest {
  private UUID id;
  private String name;
  private int dorsal;
}
