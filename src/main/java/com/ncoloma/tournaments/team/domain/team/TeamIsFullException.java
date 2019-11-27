package com.ncoloma.tournaments.team.domain.team;

class TeamIsFullException extends RuntimeException {
  TeamIsFullException(String message) {
    super(message);
  }
}
