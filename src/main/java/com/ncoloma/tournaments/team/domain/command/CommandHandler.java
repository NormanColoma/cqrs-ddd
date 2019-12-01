package com.ncoloma.tournaments.team.domain.command;

public interface CommandHandler<T extends Command> {
  ResponseCommand handle(T command);
}
