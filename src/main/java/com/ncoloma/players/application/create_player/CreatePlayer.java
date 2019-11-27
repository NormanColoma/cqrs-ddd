package com.ncoloma.players.application.create_player;

import com.ncoloma.players.domain.Player;
import com.ncoloma.players.domain.Team;
import com.ncoloma.players.domain.TeamRepository;
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
