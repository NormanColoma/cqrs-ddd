package com.ncoloma.players.application.create_player;

import com.ncoloma.players.domain.Player;
import com.ncoloma.players.domain.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CreatePlayer {
  private final PlayerRepository playerRepository;

  public UUID create(CreatePlayerRequest request) {
    UUID playerId = playerRepository.generateID();
    Player player = new Player(playerId, request.getName(), request.getDorsal(), request.getPrice());

    playerRepository.save(player);

    return playerId;
  }
}
