package com.ncoloma.players.infraestructure.persistence;

import com.ncoloma.players.domain.Player;
import com.ncoloma.players.domain.Team;
import com.ncoloma.players.domain.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class TeamH2Repository implements TeamRepository {
    private final TeamJPARepository teamJPARepository;

    @Override
    public void save(Team team) {
        Set<PlayerEntity> playerEntities = team.getPlayers().stream()
                    .map(it -> PlayerEntity.builder()
                            .id(it.getId())
                            .name(it.getName())
                            .price(it.getPrice())
                            .dorsal(it.getDorsal())
                            .team(TeamEntity.builder().id(team.getId()).build())
                            .build()
                    )
                    .collect(Collectors.toSet());

        TeamEntity teamToBeSaved = TeamEntity.builder()
                .id(team.getId())
                .name(team.getName())
                .funds(team.getFunds())
                .players(playerEntities)
                .build();


        teamToBeSaved.setPlayers(playerEntities);
        teamJPARepository.save(teamToBeSaved);
    }

    @Override
    public Optional<Team> findOne(UUID id) {
        TeamEntity teamEntity = teamJPARepository.findById(id).get();
        Set<Player> players = teamEntity.getPlayers().stream()
            .map(it -> new Player(it.getId(), it.getName(), it.getDorsal(), it.getPrice())).collect(Collectors.toSet());
        Team team = new Team(teamEntity.getId(), teamEntity.getName(), players, teamEntity.getFunds());

        return Optional.of(team);
    }

    @Override
    public UUID generateID() {
        return UUID.randomUUID();
    }
}
