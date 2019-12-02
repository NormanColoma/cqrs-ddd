package com.ncoloma.tournaments.team.application.find_team;

import com.ncoloma.tournaments.team.domain.bus.query.QueryHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindTeamQueryHandler implements QueryHandler<FindTeamQuery, FindTeamResponse> {
  private final FindTeam finder;

  @Override
  public FindTeamResponse handle(FindTeamQuery query) {
   return finder.find(query.getId());
  }
}
