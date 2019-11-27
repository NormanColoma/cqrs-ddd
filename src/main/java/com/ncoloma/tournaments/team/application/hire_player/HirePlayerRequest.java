package com.ncoloma.tournaments.team.application.hire_player;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class HirePlayerRequest {
    private UUID teamId;
    private UUID playerId;
}
