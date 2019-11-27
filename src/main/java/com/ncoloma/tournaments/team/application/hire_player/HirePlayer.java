package com.ncoloma.tournaments.team.application.hire_player;

import com.ncoloma.tournaments.team.domain.team.Player;
import com.ncoloma.tournaments.team.domain.team.PlayerRepository;
import com.ncoloma.tournaments.team.domain.team.Team;
import com.ncoloma.tournaments.team.domain.team.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HirePlayer {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public void hire(HirePlayerRequest request) {
        Player player = playerRepository.findOne(request.getPlayerId()).get();
        Team team = teamRepository.findOne(request.getTeamId()).get();

        team.hirePlayer(player);

        teamRepository.save(team);
    }
}
