package com.ncoloma.tournaments.team.domain.team;

class CannotAffordPlayerException extends RuntimeException {
  CannotAffordPlayerException(String message) {
    super(message);
  }
}
