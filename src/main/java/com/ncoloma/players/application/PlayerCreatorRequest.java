package com.ncoloma.players.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class PlayerCreatorRequest {
  private UUID id;
  private String name;
  private int dorsal;
}
