package com.ncoloma.tournaments.team.application.find_team;

import com.ncoloma.tournaments.team.domain.team.Team;
import com.ncoloma.tournaments.team.domain.team.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindTeam {
  private final TeamRepository teamRepository;

  public Team find(FindTeamRequest request) {
    return teamRepository.findOne(request.getId()).get();
  }
}
