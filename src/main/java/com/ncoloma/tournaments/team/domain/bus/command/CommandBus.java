package com.ncoloma.tournaments.team.domain.bus.command;

import java.util.UUID;

public interface CommandBus {
  UUID dispatch(Command command);
}
