package com.ncoloma.tournaments.team.infraestructure.rest;

import com.ncoloma.tournaments.team.application.create_team.CreateTeam;
import com.ncoloma.tournaments.team.application.create_team.CreateTeamRequest;
import com.ncoloma.tournaments.team.application.find_team.FindTeam;
import com.ncoloma.tournaments.team.application.find_team.FindTeamRequest;
import com.ncoloma.tournaments.team.application.find_team.FindTeamResponse;
import com.ncoloma.tournaments.team.application.hire_player.HirePlayer;
import com.ncoloma.tournaments.team.application.hire_player.HirePlayerRequest;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class TeamController {
  private final CreateTeam createTeam;
  private final HirePlayer hirePlayer;
  private final FindTeam findTeam;

  @GetMapping("/api/teams")
  public ResponseEntity<List<FindTeamResponse>> loadTeams() {
    return ResponseEntity.of(Optional.of(findTeam.find()));
  }

  @GetMapping("/api/teams/{id}")
  public ResponseEntity<FindTeamResponse> loadTeam(@PathVariable UUID id) {
    return ResponseEntity.of(Optional.of(findTeam.find(new FindTeamRequest(id))));
  }

  @PostMapping("/api/teams")
  public ResponseEntity create(@RequestBody TeamRequest teamRequest) throws URISyntaxException {
    UUID teamId = createTeam.create(new CreateTeamRequest(teamRequest.getName(), teamRequest.getFunds()));
    return ResponseEntity.created(new URI("http://localhost:8080/api/teams/" + teamId)).build();
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
  private double funds;
}

