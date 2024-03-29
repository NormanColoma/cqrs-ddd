package com.ncoloma.tournaments.team.domain.bus.command;

import java.util.UUID;

public interface CommandHandler<C extends Command> {
  UUID handle(C command);
}
