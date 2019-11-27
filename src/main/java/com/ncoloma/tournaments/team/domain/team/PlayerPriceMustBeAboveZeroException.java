package com.ncoloma.tournaments.team.domain.team;

class PlayerPriceMustBeAboveZeroException extends RuntimeException {
  PlayerPriceMustBeAboveZeroException(String message) {
    super(message);
  }
}
