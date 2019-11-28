package com.ncoloma.tournaments.team.application.update_player;

import com.ncoloma.tournaments.team.domain.team.Player;
import com.ncoloma.tournaments.team.domain.team.Team;
import com.ncoloma.tournaments.team.domain.team.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdatePlayer {

  private final TeamRepository teamRepository;

  public void update(UpdatePlayerRequest request) {
    Team team = teamRepository.findOne(request.getTeamId()).get();

    Player currentPlayer = team.getPlayers().stream().filter(it -> it.getId().equals(request.getPlayerId())).findFirst().get();
    Player playerToBeModified = new Player(request.getPlayerId(), request.getName(), request.getDorsal(), request.getPrice());

    team.modifyPlayer(currentPlayer, playerToBeModified);

    teamRepository.save(team);
  }
}
