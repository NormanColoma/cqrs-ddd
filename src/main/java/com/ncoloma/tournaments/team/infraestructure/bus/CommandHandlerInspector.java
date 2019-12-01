package com.ncoloma.tournaments.team.infraestructure.bus;

import com.ncoloma.tournaments.team.domain.command.Command;
import com.ncoloma.tournaments.team.domain.command.CommandHandler;
import org.reflections.Reflections;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Set;

@Service
public class CommandHandlerInspector {

  private HashMap<Class<? extends Command>, Class<? extends CommandHandler>> registeredHandlers;

  public CommandHandlerInspector() {
    Reflections reflections = new Reflections("com.ncoloma.tournaments");
    Set<Class<? extends CommandHandler>> commandHandlers = reflections.getSubTypesOf(CommandHandler.class);

    registeredHandlers = inspectCommandsForCommandHandlers(commandHandlers);
  }

  private HashMap<Class<? extends Command>, Class<? extends CommandHandler>> inspectCommandsForCommandHandlers(
      Set<Class<? extends CommandHandler>> commandHandlers
  ) {
    HashMap<Class<? extends Command>, Class<? extends CommandHandler>> inspectedHandlers = new HashMap<>();

    commandHandlers.forEach((handler) -> {
      ParameterizedType parameterizedType = (ParameterizedType) handler.getGenericInterfaces()[0];
      Class<? extends Command> command = (Class<? extends Command>) parameterizedType.getActualTypeArguments()[0];
      inspectedHandlers.put(command, handler);
    });

    return inspectedHandlers;
  }

  public Class<? extends CommandHandler> getCommandHandlerFor(Class<? extends Command> command) {
    return registeredHandlers.get(command);
  }
}
