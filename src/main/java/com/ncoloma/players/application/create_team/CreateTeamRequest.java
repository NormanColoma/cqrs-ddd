package com.ncoloma.players.application.create_team;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateTeamRequest {
  private String name;
  private double funds;
}
