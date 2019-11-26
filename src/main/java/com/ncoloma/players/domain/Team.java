package com.ncoloma.players.domain;


import lombok.Getter;

import java.util.Set;
import java.util.UUID;


@Getter
public class Team {
  private UUID id;
  private String name;
  private Set<Player> players;
  private double funds;

  public Team(UUID id, String name, Set<Player> players) {
    this.id = id;
    this.name = name;
    this.players = players;
    this.funds = 5000;
  }

  public void hirePlayer(Player player) {
    if (player.getPrice() >= funds) {
      throw new RuntimeException("Cannot afford this player");
    }
    player.changeTeam(this);
    this.funds -= player.getPrice();
    this.players.add(player);
  }
}
