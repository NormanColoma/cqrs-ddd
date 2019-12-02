package com.ncoloma.tournaments.team.domain.bus.query;

public interface QueryBus {
  <R extends Response> R ask(Query query);
}
