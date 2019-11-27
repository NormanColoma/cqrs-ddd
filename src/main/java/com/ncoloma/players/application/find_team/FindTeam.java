package com.ncoloma.players.application.find_team;

import com.ncoloma.players.domain.Team;
import com.ncoloma.players.domain.TeamRepository;
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
