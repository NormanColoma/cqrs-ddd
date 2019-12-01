package com.ncoloma.tournaments.team.infraestructure.bus;

import com.ncoloma.tournaments.team.domain.command.Command;
import com.ncoloma.tournaments.team.domain.command.ResponseCommand;

public interface CommandBus {
  ResponseCommand dispatch(Command command);
}
