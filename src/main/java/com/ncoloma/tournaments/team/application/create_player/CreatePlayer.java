package com.ncoloma.tournaments.team.application.create_player;

import com.ncoloma.tournaments.team.domain.team.player.Player;
import com.ncoloma.tournaments.team.domain.team.player.PlayerDetails;
import com.ncoloma.tournaments.team.domain.team.Team;
import com.ncoloma.tournaments.team.domain.team.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CreatePlayer {
  private final TeamRepository teamRepository;

  public UUID create(PlayerDetails playerDetails, UUID teamId) {
    UUID playerId = teamRepository.generateID();

    Team team = teamRepository.findOne(teamId).get();
    Player player = new Player(playerId, new PlayerDetails(playerDetails.getName(), playerDetails.getDorsal(), playerDetails.getPrice()));

    team.hirePlayer(player);
    teamRepository.save(team);

    return playerId;
  }
}
