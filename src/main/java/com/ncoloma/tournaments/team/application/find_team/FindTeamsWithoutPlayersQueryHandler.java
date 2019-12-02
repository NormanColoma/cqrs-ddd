package com.ncoloma.tournaments.team.application.find_team;

import com.ncoloma.tournaments.team.domain.bus.query.QueryHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindTeamsWithoutPlayersQueryHandler implements QueryHandler<FindTeamsWithoutPlayersQuery, FindTeamsResponse> {
  private final FindTeam finder;

  @Override
  public FindTeamsResponse handle(FindTeamsWithoutPlayersQuery query) {
   return new FindTeamsResponse(finder.findWithoutPlayers());
  }
}
