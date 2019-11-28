package com.ncoloma.tournaments.team.application.update_player;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class UpdatePlayerRequest {
  private String name;
  private double price;
  private int dorsal;
  private UUID playerId;
  private UUID teamId;
}
