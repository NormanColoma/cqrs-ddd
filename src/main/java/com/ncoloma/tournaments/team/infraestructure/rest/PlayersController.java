package com.ncoloma.tournaments.team.infraestructure.rest;

import com.ncoloma.tournaments.team.application.create_player.CreatePlayerCommand;
import com.ncoloma.tournaments.team.application.update_player.UpdatePlayerCommand;
import com.ncoloma.tournaments.team.domain.bus.command.CommandBus;
import com.ncoloma.tournaments.team.domain.team.Player;
import com.ncoloma.tournaments.team.domain.team.PlayerDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
@AllArgsConstructor
public class PlayersController {
  private final CommandBus commandBus;

  @GetMapping("/api/teams/{team}/players")
  public ResponseEntity loadPlayers(@PathVariable UUID team) {
    return ResponseEntity.ok(new Player(UUID.randomUUID(), new PlayerDetails("Messi", 10, 1000)));
  }

  @PostMapping("/api/teams/{team}/players")
  public ResponseEntity addPlayer(@PathVariable UUID team, @RequestBody PlayerRequest request) throws URISyntaxException {
    log.info("Adding player to team: " + team);

    UUID playerId = commandBus.dispatch(new CreatePlayerCommand(request.getName(), request.getDorsal(), request.getPrice(), team));
    return ResponseEntity.created(new URI("http://localhost:8080/api/players/"+playerId)).build();
  }

  @PutMapping("/api/teams/{team}/players/{player}/update")
  public ResponseEntity modifyPlayer(@PathVariable UUID team, @PathVariable UUID player, @RequestBody PlayerRequest request) {

    commandBus.dispatch(new UpdatePlayerCommand(request.getName(), request.getPrice(), request.getDorsal(), player, team));
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
