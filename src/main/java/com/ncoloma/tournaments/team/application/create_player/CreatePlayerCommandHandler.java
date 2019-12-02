package com.ncoloma.tournaments.team.application.create_player;

import com.ncoloma.tournaments.team.application.hire_player.HirePlayer;
import com.ncoloma.tournaments.team.application.hire_player.HirePlayerCommand;
import com.ncoloma.tournaments.team.application.hire_player.HirePlayerRequest;
import com.ncoloma.tournaments.team.domain.bus.command.CommandHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CreatePlayerCommandHandler implements CommandHandler<CreatePlayerCommand> {
  private final CreatePlayer playerCreator;

  @Override
  public UUID handle(CreatePlayerCommand command) {
    return playerCreator.create(new CreatePlayerRequest(command.getName(), command.getDorsal(), command.getPrice(), command.getTeamId()));
  }
}
