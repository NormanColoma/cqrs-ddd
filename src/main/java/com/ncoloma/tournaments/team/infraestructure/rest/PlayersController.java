package com.ncoloma.tournaments.team.infraestructure.rest;

import com.ncoloma.tournaments.team.application.create_player.CreatePlayer;
import com.ncoloma.tournaments.team.application.create_player.CreatePlayerRequest;
import com.ncoloma.tournaments.team.application.update_player.UpdatePlayer;
import com.ncoloma.tournaments.team.application.update_player.UpdatePlayerRequest;
import com.ncoloma.tournaments.team.domain.team.Player;
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
  private final CreatePlayer playerCreator;
  private final UpdatePlayer updatePlayer;

  @GetMapping("/api/teams/{team}/players")
  public ResponseEntity loadPlayers(@PathVariable UUID team) {
    return ResponseEntity.ok(new Player(UUID.randomUUID(), "Messi", 10, 1000));
  }

  @PostMapping("/api/teams/{team}/players")
  public ResponseEntity addPlayer(@PathVariable UUID team, @RequestBody PlayerRequest request) throws URISyntaxException {
    UUID playerId = playerCreator.create(new CreatePlayerRequest(request.getName(), request.getDorsal(), request.getPrice(), team));
    return ResponseEntity.created(new URI("http://localhost:8080/api/players/"+playerId)).build();
  }

  @PutMapping("/api/teams/{team}/players/{player}/update")
  public ResponseEntity modifyPlayer(@PathVariable UUID team, @PathVariable UUID player, @RequestBody PlayerRequest request) {
    updatePlayer.update(new UpdatePlayerRequest(request.getName(), request.getPrice(), request.getDorsal(), player, team));
    return ResponseEntity.noContent().build();
  }

}

@Getter
@AllArgsConstructor
final class PlayerRequest {
  private String name;
  private int dorsal;
  private double price;
}