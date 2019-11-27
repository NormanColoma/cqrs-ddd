package com.ncoloma.tournaments.team.application.find_team;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
@Builder
public class FindTeamResponse {
  private String name;
  private double currentFunds;
  private Set<PlayerResponse> players;
  private int totalPlayers;
  private String id;
}

@AllArgsConstructor
@Builder
@Getter
final class PlayerResponse {
  private String name;
  private int dorsal;
}
