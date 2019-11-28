package com.ncoloma.tournaments.team.infraestructure.persistence.h2;

import com.ncoloma.tournaments.team.domain.team.Player;
import com.ncoloma.tournaments.team.domain.team.Team;
import com.ncoloma.tournaments.team.domain.team.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

@Repository
@AllArgsConstructor
public class TeamH2Repository implements TeamRepository {
    private final TeamJPARepository teamJPARepository;

    @Override
    public void save(Team team) {
        teamJPARepository.save(mapToEntity(team));
    }

    @Override
    public Optional<Team> findOne(UUID id) {
        Team team = mapToDomain(teamJPARepository.findById(id).orElse(null));

        return Objects.isNull(team) ? Optional.empty() : Optional.of(team);
    }

    @Override
    public Optional<Team> findOneWithPlayer(UUID playerId) {
        TeamWithPlayer teamWithPlayer = new ArrayList<>(teamJPARepository.findByPlayersId(playerId)).get(0);
        PlayerEntity playerEntity = teamWithPlayer.getPlayers();
        Player player = new Player(playerEntity.getId(), playerEntity.getName(), playerEntity.getDorsal(), playerEntity.getPrice());
        Team team = new Team(teamWithPlayer.getId(), teamWithPlayer.getName(), Stream.of(player).collect(Collectors.toSet()), teamWithPlayer.getFunds());

        return Optional.of(team);
    }

    @Override
    public List<Team> findAll() {
        return teamJPARepository.findAll()
            .stream()
            .map(this::mapToDomain)
            .collect(Collectors.toList());
    }

    @Override
    public UUID generateID() {
        return UUID.randomUUID();
    }


    private Team mapToDomain(TeamEntity team) {
        if (Objects.nonNull(team)) {
            Set<Player> players = team.getPlayers().stream()
                .map(it -> new Player(it.getId(), it.getName(), it.getDorsal(), it.getPrice())).collect(Collectors.toSet());

            return new Team(team.getId(), team.getName(), players, team.getFunds());
        }
       return null;
    }

    private TeamEntity mapToEntity(Team team) {
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

        return TeamEntity.builder()
            .id(team.getId())
            .name(team.getName())
            .funds(team.getFunds())
            .players(playerEntities)
            .build();
    }
}
