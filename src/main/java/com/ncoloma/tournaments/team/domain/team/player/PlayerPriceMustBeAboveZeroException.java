package com.ncoloma.tournaments.team.domain.team.player;

class PlayerPriceMustBeAboveZeroException extends RuntimeException {
  PlayerPriceMustBeAboveZeroException(String message) {
    super(message);
  }
}
