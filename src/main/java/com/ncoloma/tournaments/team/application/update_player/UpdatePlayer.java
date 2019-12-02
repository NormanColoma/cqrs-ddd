package com.ncoloma.tournaments.team.application.update_player;

import com.ncoloma.tournaments.team.domain.team.Player;
import com.ncoloma.tournaments.team.domain.team.PlayerDetails;
import com.ncoloma.tournaments.team.domain.team.Team;
import com.ncoloma.tournaments.team.domain.team.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UpdatePlayer {

  private final TeamRepository teamRepository;

  public void update(UUID playerId, PlayerDetails newDetails) {
    Team team = teamRepository.findOneWithPlayer(playerId).get();

    Player currentPlayer = team.getPlayers()
        .stream()
        .filter(it -> it.getId().equals(playerId))
        .findFirst().get();

    Player playerToBeModified = new Player(playerId, new PlayerDetails(newDetails.getName(), newDetails.getDorsal(), newDetails.getPrice()));

    team.modifyPlayer(currentPlayer, playerToBeModified);

    teamRepository.save(team);
  }
}
