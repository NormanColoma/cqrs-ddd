package com.ncoloma.tournaments.team.domain.bus.query;

public interface QueryHandler<Q extends Query, R extends Response> {
  R handle(Q query);
}
