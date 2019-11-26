package com.ncoloma.players.infraestructure.rest;

import com.ncoloma.players.application.create_team.CreateTeam;
import com.ncoloma.players.application.create_team.CreateTeamRequest;
import com.ncoloma.players.application.hire_player.HirePlayer;
import com.ncoloma.players.application.hire_player.HirePlayerRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
public class TeamController {
  private final CreateTeam createTeam;
  private final HirePlayer hirePlayer;

  @PostMapping("/api/teams")
  public ResponseEntity create(@RequestBody TeamRequest teamRequest) throws URISyntaxException {
    UUID teamId = createTeam.create(new CreateTeamRequest(teamRequest.getName()));
    return ResponseEntity.created(new URI("/api/teams/" + teamId)).build();
  }

  @PutMapping("/api/teams/{teamId}/players/{playerId}")
  public ResponseEntity hirePlayer(@PathVariable UUID teamId, @PathVariable UUID playerId) {
    hirePlayer.hire(new HirePlayerRequest(teamId, playerId));
    return ResponseEntity.noContent().build();
  }
}

@Getter
@AllArgsConstructor
@NoArgsConstructor
final class TeamRequest {
  private String name;
}

