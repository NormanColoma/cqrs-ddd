package com.ncoloma.players.application;

import com.ncoloma.players.domain.Player;
import com.ncoloma.players.domain.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlayerCreator {
  private final PlayerRepository playerRepository;

  public void create(PlayerCreatorRequest playerCreatorRequest) {
    Player player = new Player(playerCreatorRequest.getId(), playerCreatorRequest.getName(), playerCreatorRequest.getDorsal());

    playerRepository.save(player);
  }
}
