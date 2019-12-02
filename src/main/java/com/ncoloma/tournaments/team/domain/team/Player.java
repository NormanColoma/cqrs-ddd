package com.ncoloma.tournaments.team.domain.team;

import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public class Player {
  private UUID id;
  private PlayerDetails details;
  private TeamId teamId;

  public Player(UUID id, PlayerDetails details){
    this.id = id;
    this.details = details;
  }

  void team(TeamId teamId) {
    if (Objects.isNull(teamId)) {
      throw new PlayerMustBelongToATeamException("A player must have a team");
    }
    this.teamId = teamId;
  }

  public void modify(PlayerDetails details) {
    this.details = new PlayerDetails(details.getName(), details.getDorsal(), details.getPrice());
  }

}
