package com.ncoloma.tournaments.team.application.update_player;

import com.ncoloma.tournaments.team.domain.bus.command.CommandHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UpdatePlayerCommandHandler implements CommandHandler<UpdatePlayerCommand> {
  private final UpdatePlayer updatePlayer;

  @Override
  public UUID handle(UpdatePlayerCommand command) {
    updatePlayer.update(new UpdatePlayerRequest(command.getName(), command.getPrice(), command.getDorsal(), command.getPlayerId(), command.getTeamId()));
    return command.getPlayerId();
  }
}
