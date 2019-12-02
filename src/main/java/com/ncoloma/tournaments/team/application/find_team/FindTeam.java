package com.ncoloma.tournaments.team.application.find_team;

import com.ncoloma.tournaments.team.domain.team.Team;
import com.ncoloma.tournaments.team.domain.team.TeamNotFoundException;
import com.ncoloma.tournaments.team.domain.team.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FindTeam {
    private final TeamRepository teamRepository;

    public FindTeamResponse find(UUID teamId) {
        Team team = teamRepository.findOne(teamId).orElseThrow(() -> new TeamNotFoundException(String.format("Team %s does not exist", teamId)));
        return toResponse(team);
    }

    public List<FindTeamResponse> find() {
        return teamRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<FindTeamResponse> findWithoutPlayers() {
        return teamRepository.findAllWithoutPlayers().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private FindTeamResponse toResponse(Team team) {

        return FindTeamResponse.builder()
                .id(team.getId().toString())
                .name(team.getName())
                .currentFunds(team.getFunds())
                .totalPlayers(team.getPlayers().size())
                .players(team.getPlayers()
                        .stream()
                        .map(player -> PlayerResponse.builder().name(player.getName()).dorsal(player.getDorsal()).build())
                        .collect(Collectors.toSet())
                )
                .build();
    }
}
