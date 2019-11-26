package com.ncoloma.players.domain;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Player {
  private UUID id;
  private String name;
  private int dorsal;

  public Player(UUID id, String name, int dorsal){
    this.id = id;
    this.name = name;
    this.dorsal = dorsal;
  }

  public void changeDorsal(int dorsal) {
    this.dorsal = dorsal;
  }
}
