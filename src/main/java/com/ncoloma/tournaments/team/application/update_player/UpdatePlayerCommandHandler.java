package com.ncoloma.tournaments.team.application.update_player;

import com.ncoloma.tournaments.team.domain.bus.command.CommandHandler;
import com.ncoloma.tournaments.team.domain.team.player.PlayerDetails;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UpdatePlayerCommandHandler implements CommandHandler<UpdatePlayerCommand> {
  private final UpdatePlayer updatePlayer;

  @Override
  public UUID handle(UpdatePlayerCommand command) {
    updatePlayer.update(command.getPlayerId(), new PlayerDetails(command.getName(), command.getDorsal(), command.getPrice()));
    return command.getPlayerId();
  }
}
