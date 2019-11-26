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
        TeamEntity teamToBeSaved = TeamEntity.builder()
                .id(team.getId())
                .name(team.getName())
                .funds(team.getFunds())
                .build();

        Set<Player> players = team.getPlayers();
        Set<PlayerEntity> playerEntities = null;
        if (Objects.nonNull(team.getPlayers())) {
            playerEntities = players.stream()
                    .map(it -> PlayerEntity.builder()
                            .id(it.getId())
                            .name(it.getName())
                            .price(it.getPrice())
                            .dorsal(it.getDorsal())
                            .team(teamToBeSaved)
                            .build()
                    )
                    .collect(Collectors.toSet());
        }

        teamToBeSaved.setPlayers(playerEntities);
        teamJPARepository.save(teamToBeSaved);
    }

    @Override
    public Optional<Team> findOne(UUID id) {
        TeamEntity teamEntity = teamJPARepository.findById(id).get();

        Set<Player> players = new HashSet<>();
        if (Objects.nonNull(teamEntity.getPlayers())) {
            teamEntity.getPlayers().stream()
                    .map(it -> new Player(it.getId(), it.getName(), it.getDorsal(), it.getPrice()));
        }
        Team team = new Team(teamEntity.getId(), teamEntity.getName(), players);

        return Optional.of(team);
    }
}
