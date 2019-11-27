package com.ncoloma.tournaments.team.application.update_player;

import com.ncoloma.tournaments.team.domain.team.Player;
import com.ncoloma.tournaments.team.domain.team.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdatePlayer {

  private final PlayerRepository playerRepository;

  public void update(UpdatePlayerRequest updatePlayer) {
    Player player = playerRepository.findOne(updatePlayer.getId()).get();

    player.changeDorsal(updatePlayer.getDorsal());

    playerRepository.save(player);
  }
}
