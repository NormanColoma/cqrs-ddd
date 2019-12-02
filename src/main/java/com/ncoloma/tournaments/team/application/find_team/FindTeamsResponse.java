package com.ncoloma.tournaments.team.application.find_team;

import com.ncoloma.tournaments.team.domain.bus.query.Response;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class FindTeamsResponse implements Response {
  private final List<FindTeamResponse> teams;

  public FindTeamsResponse(List<FindTeamResponse> teams) {
    this.teams = teams;
  }
}
