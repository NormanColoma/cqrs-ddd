package com.ncoloma.tournaments.team.application.find_team;

import com.ncoloma.tournaments.team.domain.command.CommandHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindTeamCommandHandler implements CommandHandler<FindTeamCommand> {
  private final FindTeam finder;

  @Override
  public FindTeamResponse handle(FindTeamCommand command) {
   return finder.find(command.getId());
  }
}
