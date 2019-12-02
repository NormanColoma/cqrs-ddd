package com.ncoloma.tournaments.team.application.create_player;


import com.ncoloma.tournaments.team.domain.bus.command.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class CreatePlayerCommand implements Command {
  private String name;
  private int dorsal;
  private double price;
  private UUID teamId;
}
