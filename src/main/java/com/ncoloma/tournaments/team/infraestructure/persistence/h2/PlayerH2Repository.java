package com.ncoloma.tournaments.team.infraestructure.persistence.h2;

import com.ncoloma.tournaments.team.domain.team.Player;
import com.ncoloma.tournaments.team.domain.team.PlayerDetails;
import com.ncoloma.tournaments.team.domain.team.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PlayerH2Repository implements PlayerRepository {

  private final PlayerJPARepository playerJPARepository;

  @Override
  public void save(Player player) {
    PlayerEntity playerToSave = PlayerEntity.builder()
        .id(player.getId())
        .name(player.getDetails().getName())
        .dorsal(player.getDetails().getDorsal())
        .price(player.getDetails().getPrice())
        .build();

    playerJPARepository.save(playerToSave);
  }

  @Override
  public Set<Optional<Player>> find() {
    return null;
  }

  @Override
  public Optional<Player> findOne(UUID id) {
    PlayerEntity player = playerJPARepository.findById(id).get();

    return Optional.of(new Player(player.getId(), new PlayerDetails(player.getName(), player.getDorsal(), player.getPrice())));
  }

  @Override
  public UUID generateID() {
    return UUID.randomUUID();
  }
}
