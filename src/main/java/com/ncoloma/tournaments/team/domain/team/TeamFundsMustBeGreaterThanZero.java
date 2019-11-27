package com.ncoloma.tournaments.team.domain.team;

class TeamFundsMustBeGreaterThanZero extends RuntimeException {
  TeamFundsMustBeGreaterThanZero(String message) {
    super(message);
  }
}
