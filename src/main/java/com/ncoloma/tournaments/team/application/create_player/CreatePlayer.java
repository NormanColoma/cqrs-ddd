package com.ncoloma.tournaments.team.application.create_player;

import com.ncoloma.tournaments.team.domain.team.Player;
import com.ncoloma.tournaments.team.domain.team.Team;
import com.ncoloma.tournaments.team.domain.team.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CreatePlayer {
  private final TeamRepository teamRepository;

  public UUID create(CreatePlayerRequest request) {
    UUID playerId = teamRepository.generateID();

    Team team = teamRepository.findOne(request.getTeamId()).get();
    Player player = new Player(playerId, request.getName(), request.getDorsal(), request.getPrice());

    team.hirePlayer(player);
    teamRepository.save(team);

    return playerId;
  }
}
