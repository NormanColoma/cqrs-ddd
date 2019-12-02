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
  public UUID create(String name, double funds) {
    UUID teamId = teamRepository.generateID();
    Team team = new Team(teamId, name, new HashSet<>(), funds);

    teamRepository.save(team);

    return teamId;
  }
}
