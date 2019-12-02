package com.ncoloma.tournaments.team.infraestructure.persistence.h2;

import com.ncoloma.tournaments.team.domain.team.player.Player;
import com.ncoloma.tournaments.team.domain.team.player.PlayerDetails;
import com.ncoloma.tournaments.team.domain.team.Team;
import com.ncoloma.tournaments.team.domain.team.TeamId;
import com.ncoloma.tournaments.team.domain.team.TeamRepository;
import com.ncoloma.tournaments.team.domain.team.player.PlayerId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        Player player = new Player(new PlayerId(playerEntity.getId()), new PlayerDetails(playerEntity.getName(), playerEntity.getDorsal(), playerEntity.getPrice()));
        Team team = new Team(new TeamId(teamWithPlayer.getId()), teamWithPlayer.getName(), Stream.of(player).collect(Collectors.toSet()), teamWithPlayer.getFunds());

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
    public List<Team> findAllWithoutPlayers() {
        return teamJPARepository.findAll()
            .stream()
            .map(this::mapToDomainWithoutPlayers)
            .collect(Collectors.toList());
    }

    @Override
    public UUID generateID() {
        return UUID.randomUUID();
    }


    private Team mapToDomain(TeamEntity team) {
        if (Objects.nonNull(team)) {
            Set<Player> players = team.getPlayers().stream()
                .map(it -> new Player(new PlayerId(it.getId()), new PlayerDetails(it.getName(), it.getDorsal(), it.getPrice()))).collect(Collectors.toSet());

            return new Team(new TeamId(team.getId()), team.getName(), players, team.getFunds());
        }
       return null;
    }

    private Team mapToDomainWithoutPlayers(TeamEntity team) {
        if (Objects.nonNull(team)) {
            return new Team(new TeamId(team.getId()), team.getName(), new HashSet<>(), team.getFunds());
        }
       return null;
    }

    private TeamEntity mapToEntity(Team team) {
        Set<PlayerEntity> playerEntities = team.getPlayers().stream()
            .map(it -> PlayerEntity.builder()
                .id(it.getId().getId())
                .name(it.getDetails().getName())
                .price(it.getDetails().getPrice())
                .dorsal(it.getDetails().getDorsal())
                .team(TeamEntity.builder().id(team.getId().getId()).build())
                .build()
            )
            .collect(Collectors.toSet());

        return TeamEntity.builder()
            .id(team.getId().getId())
            .name(team.getName())
            .funds(team.getFunds())
            .players(playerEntities)
            .build();
    }
}
