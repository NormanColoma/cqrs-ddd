package com.ncoloma.tournaments.team.application.create_team;

import com.ncoloma.tournaments.team.domain.team.Team;
import com.ncoloma.tournaments.team.domain.team.TeamRepository;
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
    Team team = new Team(teamId, request.getName(), new HashSet<>(), request.getFunds());

    teamRepository.save(team);

    return teamId;
  }
}
