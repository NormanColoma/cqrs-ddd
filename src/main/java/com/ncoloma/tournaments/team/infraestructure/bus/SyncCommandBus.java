package com.ncoloma.tournaments.team.infraestructure.bus;

import com.ncoloma.tournaments.team.domain.command.Command;
import com.ncoloma.tournaments.team.domain.command.CommandHandler;
import com.ncoloma.tournaments.team.domain.command.ResponseCommand;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SyncCommandBus implements CommandBus {
  private final CommandHandlerInspector commandHandlerInspector;
  private final ApplicationContext context;


  @Override
  public ResponseCommand dispatch(Command command) {
    Class<? extends CommandHandler> commandHandlerClass = commandHandlerInspector.getCommandHandlerFor(command.getClass());

    CommandHandler handler = context.getBean(commandHandlerClass);

    return handler.handle(command);
  }
}
