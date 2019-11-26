package com.ncoloma.players.infraestructure.rest;

import com.ncoloma.players.application.PlayerCreator;
import com.ncoloma.players.application.PlayerCreatorRequest;
import com.ncoloma.players.application.PlayerUpdater;
import com.ncoloma.players.domain.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class PlayersController {
  private final PlayerCreator playerCreator;
  private final PlayerUpdater playerUpdater;

  @GetMapping("/api/players")
  public ResponseEntity loadPlayers() {
    return ResponseEntity.ok(new Player(UUID.randomUUID(), "Messi", 10));
  }

  @PostMapping("/api/players")
  public ResponseEntity addPlayer(@RequestBody PlayerRequest request) throws URISyntaxException {
    UUID playerId = UUID.randomUUID();
    playerCreator.create(new PlayerCreatorRequest(playerId, request.getName(), request.getDorsal()));
    return ResponseEntity.created(new URI("/api/players/"+playerId)).build();
  }

  @PutMapping("/api/players/{id}")
  public ResponseEntity updatePlayer(@PathVariable("id") UUID id, @RequestBody PlayerRequest request) {

    playerUpdater.update(new PlayerCreatorRequest(id, request.getName(), request.getDorsal()));
    return ResponseEntity.noContent().build();
  }
}

@Getter
@AllArgsConstructor
final class PlayerRequest {
  private String name;
  private int dorsal;
}