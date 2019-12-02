package com.ncoloma.tournaments.team.application.create_team;


import com.ncoloma.tournaments.team.domain.bus.command.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateTeamCommand implements Command {
  private String name;
  private double funds;
}
