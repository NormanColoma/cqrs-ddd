package com.ncoloma.tournaments.team.infraestructure.persistence.h2;

import com.ncoloma.tournaments.team.domain.team.Player;
import com.ncoloma.tournaments.team.domain.team.Team;
import com.ncoloma.tournaments.team.domain.team.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

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
        teamJPARepository.save(mapToEntity(team));
    }

    @Override
    public Optional<Team> findOne(UUID id) {
        return mapToDomain(teamJPARepository.findById(id));
    }

    @Override
    public UUID generateID() {
        return UUID.randomUUID();
    }


    private Optional<Team> mapToDomain(Optional<TeamEntity> teamEntity) {
        if (teamEntity.isPresent()) {
            TeamEntity teamEntityRetrieved = teamEntity.get();
            Set<Player> players = teamEntityRetrieved.getPlayers().stream()
                .map(it -> new Player(it.getId(), it.getName(), it.getDorsal(), it.getPrice())).collect(Collectors.toSet());

            return Optional.of(new Team(teamEntityRetrieved.getId(), teamEntityRetrieved.getName(), players, teamEntityRetrieved.getFunds()));
        }
       return Optional.empty();
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
