package com.ncoloma.tournaments.team.infraestructure.bus.command;

import com.ncoloma.tournaments.team.domain.bus.command.Command;
import com.ncoloma.tournaments.team.domain.bus.command.CommandBus;
import com.ncoloma.tournaments.team.domain.bus.command.CommandHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class SyncCommandBus implements CommandBus {
  private final CommandHandlerInspector commandHandlerInspector;
  private final ApplicationContext context;

  @Override
  public UUID dispatch(Command command) {
    Class<? extends CommandHandler> queryHandlerClass = commandHandlerInspector.searchCommandHandlerFor(command.getClass());

    CommandHandler handler = context.getBean(queryHandlerClass);

    return handler.handle(command);
  }
}
