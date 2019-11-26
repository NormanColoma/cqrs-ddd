package com.ncoloma.players.infraestructure.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

  @PostMapping("/api/teams")
  public ResponseEntity create() {
    return ResponseEntity.ok().build();
  }
}

@Getter
@AllArgsConstructor
final class TeamRequest {
  private String name;
}
