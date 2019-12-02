package com.ncoloma.tournaments.team.application.hire_player;


import com.ncoloma.tournaments.team.domain.bus.command.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class HirePlayerCommand implements Command {
  private UUID teamId;
  private UUID playerId;
}
