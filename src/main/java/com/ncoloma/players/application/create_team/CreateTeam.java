package com.ncoloma.players.application.create_team;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateTeam {

  public UUID create(CreateTeamRequest request) {
    return UUID.randomUUID();
  }
}
