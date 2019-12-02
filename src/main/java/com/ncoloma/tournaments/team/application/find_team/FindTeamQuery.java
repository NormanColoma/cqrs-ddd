package com.ncoloma.tournaments.team.application.find_team;

import com.ncoloma.tournaments.team.domain.bus.query.Query;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class FindTeamQuery implements Query {
  UUID id;
}
