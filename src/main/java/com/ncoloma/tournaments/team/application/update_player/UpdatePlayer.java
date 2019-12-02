package com.ncoloma.tournaments.team.application.update_player;

import com.ncoloma.tournaments.team.domain.team.player.Player;
import com.ncoloma.tournaments.team.domain.team.player.PlayerDetails;
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

    currentPlayer.modify(new PlayerDetails(newDetails.getName(), newDetails.getDorsal(), newDetails.getPrice()));

    team.modifyPlayer(currentPlayer);

    teamRepository.save(team);
  }
}
