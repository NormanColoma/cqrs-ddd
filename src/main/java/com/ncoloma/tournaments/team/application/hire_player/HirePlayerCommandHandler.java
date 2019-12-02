package com.ncoloma.tournaments.team.application.hire_player;

import com.ncoloma.tournaments.team.domain.bus.command.CommandHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class HirePlayerCommandHandler implements CommandHandler<HirePlayerCommand> {
  private final HirePlayer hirePlayer;

  @Override
  public UUID handle(HirePlayerCommand command) {
    hirePlayer.hire(new HirePlayerRequest(command.getTeamId(), command.getPlayerId()));
    return command.getPlayerId();
  }
}
