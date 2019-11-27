package com.ncoloma.tournaments.team.domain.team;

public class TeamNotFoundException extends RuntimeException {
  public TeamNotFoundException(String message) {
    super(message);
  }
}
