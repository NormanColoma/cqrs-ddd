package com.ncoloma.players.application.create_team;

import com.ncoloma.players.domain.Team;
import com.ncoloma.players.domain.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CreateTeam {
  private final TeamRepository teamRepository;
  public UUID create(CreateTeamRequest request) {
    UUID teamId = UUID.randomUUID();
    Team team = new Team(teamId, request.getName(), new HashSet<>());

    teamRepository.save(team);

    return teamId;
  }
}
