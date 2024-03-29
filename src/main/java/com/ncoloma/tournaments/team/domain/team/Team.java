package com.ncoloma.tournaments.team.domain.team;


import com.ncoloma.tournaments.team.domain.team.player.Player;
import lombok.Getter;

import java.util.Set;


@Getter
public class Team {
  private TeamId id;
  private String name;
  private Set<Player> players;
  private double funds;
  private static final int MAX_PLAYERS = 18;

  public Team(TeamId id, String name, Set<Player> players, double funds) {
    this.id = id;
    this.name = name;
    this.players = players;
    funds(funds);
  }

  public void hirePlayer(Player player) {
    // Comment this if you want to bulk insert for performance
    if (players.size() == MAX_PLAYERS) {
      throw new TeamIsFullException("Team already has 18 players hired");
    }
    if (player.getDetails().getPrice() >= funds) {
      throw new CannotAffordPlayerException("Cannot afford this player");
    }

    player.team(id);
    funds(funds - player.getDetails().getPrice());
    players.add(player);
  }

  public void modifyPlayer(Player currentPlayer) {
    players.remove(currentPlayer);
    players.add(currentPlayer);
  }
  private void funds(double funds) {
    if (funds <= 0) {
      throw new TeamFundsMustBeGreaterThanZero("Funds of a team must be greater than 0 euros");
    }
    this.funds = funds;
  }
}
