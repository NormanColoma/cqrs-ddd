package com.ncoloma.players.application;

import com.ncoloma.players.domain.Player;
import com.ncoloma.players.domain.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlayerUpdater {

  private final PlayerRepository playerRepository;

  public void update(PlayerCreatorRequest playerCreatorRequest) {
    Player player = playerRepository.findOne(playerCreatorRequest.getId()).get();

    player.changeDorsal(playerCreatorRequest.getDorsal());

    playerRepository.save(player);
  }
}
