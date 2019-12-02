package com.ncoloma.tournaments.team.application.update_player;


import com.ncoloma.tournaments.team.domain.bus.command.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class UpdatePlayerCommand implements Command {
  private String name;
  private double price;
  private int dorsal;
  private UUID playerId;
  private UUID teamId;
}
