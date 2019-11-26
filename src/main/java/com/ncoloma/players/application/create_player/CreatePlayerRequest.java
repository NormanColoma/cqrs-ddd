package com.ncoloma.players.application.create_player;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreatePlayerRequest {
  private String name;
  private int dorsal;
}
