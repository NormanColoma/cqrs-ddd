package com.ncoloma.tournaments.team.domain.team;

class PlayerMustBelongToATeamException extends RuntimeException {
  PlayerMustBelongToATeamException(String message) {
    super(message);
  }
}
