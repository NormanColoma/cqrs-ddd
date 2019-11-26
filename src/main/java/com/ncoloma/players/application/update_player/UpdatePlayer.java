package com.ncoloma.players.application.update_player;

import com.ncoloma.players.domain.Player;
import com.ncoloma.players.domain.PlayerRepository;
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
