package com.ncoloma.tournaments.team.application.create_player;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class CreatePlayerRequest {
  private String name;
  private int dorsal;
  private double price;
  private UUID teamId;
}
