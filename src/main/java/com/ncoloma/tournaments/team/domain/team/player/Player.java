package com.ncoloma.tournaments.team.domain.team.player;

import com.ncoloma.tournaments.team.domain.team.TeamId;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Player {
  private PlayerId id;
  private PlayerDetails details;
  private TeamId teamId;

  public Player(PlayerId id, PlayerDetails details){
    this.id = id;
    this.details = details;
  }

  public void team(TeamId teamId) {
    if (Objects.isNull(teamId)) {
      throw new PlayerMustBelongToATeamException("A player must have a team");
    }
    this.teamId = teamId;
  }

  public void modify(PlayerDetails details) {
    this.details = new PlayerDetails(details.getName(), details.getDorsal(), details.getPrice());
  }

}
