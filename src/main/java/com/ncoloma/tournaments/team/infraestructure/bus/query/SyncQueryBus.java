package com.ncoloma.tournaments.team.infraestructure.bus;

import com.ncoloma.tournaments.team.domain.bus.query.Query;
import com.ncoloma.tournaments.team.domain.bus.query.QueryBus;
import com.ncoloma.tournaments.team.domain.bus.query.QueryHandler;
import com.ncoloma.tournaments.team.domain.bus.Response;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SyncQueryBus implements QueryBus {
  private final QueryHandlerInspector queryHandlerInspector;
  private final ApplicationContext context;

  @Override
  public Response ask(Query query) {
    Class<? extends QueryHandler> queryHandlerClass = queryHandlerInspector.searchQueryHandlerFor(query.getClass());

    QueryHandler handler = context.getBean(queryHandlerClass);

    return handler.handle(query);
  }
}
