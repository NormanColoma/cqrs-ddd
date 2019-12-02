package com.ncoloma.tournaments.team.domain.team.player;

class PlayerMustBelongToATeamException extends RuntimeException {
  PlayerMustBelongToATeamException(String message) {
    super(message);
  }
}
