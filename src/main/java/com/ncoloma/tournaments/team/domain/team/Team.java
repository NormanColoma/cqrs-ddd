package com.ncoloma.tournaments.team.domain.team;


import lombok.Getter;

import java.util.Set;
import java.util.UUID;


@Getter
public class Team {
  private UUID id;
  private String name;
  private Set<Player> players;
  private double funds;
  private static final int MAX_PLAYERS = 18;

  public Team(UUID id, String name, Set<Player> players, double funds) {
    this.id = id;
    this.name = name;
    this.players = players;
    funds(funds);
  }

  public void hirePlayer(Player player) {
    if (players.size() == MAX_PLAYERS) {
      throw new TeamIsFullException("Team already has 18 players hired");
    }
    if (player.getPrice() >= funds) {
      throw new CannotAffordPlayerException("Cannot afford this player");
    }

    player.team(this);
    funds(funds - player.getPrice());
    players.add(player);
  }

  private void funds(double funds) {
    if (funds <= 0) {
      throw new TeamFundsMustBeGreaterThanZero("Funds of a team must be greater than 0 euros");
    }
    this.funds = funds;
  }
}
