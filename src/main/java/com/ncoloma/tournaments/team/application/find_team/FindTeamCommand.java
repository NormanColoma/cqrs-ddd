package com.ncoloma.tournaments.team.application.find_team;

import com.ncoloma.tournaments.team.domain.command.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class FindTeamCommand implements Command {
  UUID id;
}
