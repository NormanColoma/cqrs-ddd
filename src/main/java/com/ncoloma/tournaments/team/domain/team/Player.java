package com.ncoloma.tournaments.team.domain.team;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Player {
  private UUID id;
  private String name;
  private int dorsal;
  private double price;
  private Team team;

  public Player(UUID id, String name, int dorsal, double price){
    this.id = id;
    this.name = name;
    dorsal(dorsal);
    price(price);
  }

  public void changeDorsal(int dorsal) {
    this.dorsal = dorsal;
  }

  void changeTeam(Team team) {
    this.team = team;
  }

  private void price(double price) {
    if (price <= 0) {
      throw new PlayerPriceMustBeAboveZeroException("Price must be above 0 euros");
    }
    this.price = price;
  }

  private void dorsal(int dorsal) {
    if (dorsal >= 100) {
      throw new InvalidPlayerDorsal("Dorsal of a player must be between 0 and 99");
    }
    this.dorsal = dorsal;
  }
}
