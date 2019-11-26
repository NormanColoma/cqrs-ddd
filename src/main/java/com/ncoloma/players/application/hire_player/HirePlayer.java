package com.ncoloma.players.application.hire_player;

import com.ncoloma.players.domain.Player;
import com.ncoloma.players.domain.PlayerRepository;
import com.ncoloma.players.domain.Team;
import com.ncoloma.players.domain.TeamRepository;
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
