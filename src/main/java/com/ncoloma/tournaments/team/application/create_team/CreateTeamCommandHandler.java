package com.ncoloma.tournaments.team.application.create_team;

import com.ncoloma.tournaments.team.domain.bus.command.CommandHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CreateTeamCommandHandler implements CommandHandler<CreateTeamCommand> {
  private final CreateTeam creator;
  @Override
  public UUID handle(CreateTeamCommand command) {
    return creator.create(command.getName(), command.getFunds());
  }
}
