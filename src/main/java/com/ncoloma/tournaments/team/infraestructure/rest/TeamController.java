package com.ncoloma.tournaments.team.infraestructure.rest;

import com.ncoloma.tournaments.team.application.create_team.CreateTeamCommand;
import com.ncoloma.tournaments.team.application.find_team.FindTeam;
import com.ncoloma.tournaments.team.application.find_team.FindTeamQuery;
import com.ncoloma.tournaments.team.application.find_team.FindTeamResponse;
import com.ncoloma.tournaments.team.application.hire_player.HirePlayer;
import com.ncoloma.tournaments.team.application.hire_player.HirePlayerRequest;
import com.ncoloma.tournaments.team.domain.bus.command.CommandBus;
import com.ncoloma.tournaments.team.domain.bus.query.QueryBus;
import com.ncoloma.tournaments.team.domain.bus.query.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Slf4j
public class TeamController {
  private final HirePlayer hirePlayer;
  private final FindTeam findTeam;
  private final QueryBus queryBus;
  private final CommandBus commandBus;

  @GetMapping("/api/teams")
  public ResponseEntity<List<FindTeamResponse>> loadTeams() {
    return ResponseEntity.of(Optional.of(findTeam.find()));
  }

  @GetMapping("/api/teams/only")
  public ResponseEntity<List<FindTeamResponse>> loadTeamsWithoutPlayers() {
    return ResponseEntity.of(Optional.of(findTeam.findWithoutPlayers()));
  }

  @GetMapping("/api/teams/{id}")
  public ResponseEntity<Response> loadTeam(@PathVariable UUID id) {
    log.info("Getting info about team: " + id);
    return ResponseEntity.of(Optional.of(queryBus.ask(new FindTeamQuery(id))));
  }

  @PostMapping("/api/teams")
  public ResponseEntity<UUID> create(@RequestBody TeamRequest teamRequest) throws URISyntaxException {
    UUID teamId = commandBus.dispatch(new CreateTeamCommand(teamRequest.getName(), teamRequest.getFunds()));
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

