package com.ncoloma.tournaments.team.domain.team;

import lombok.Getter;

@Getter
public class PlayerDetails {
  private String name;
  private int dorsal;
  private double price;

  public PlayerDetails(String name, int dorsal, double price) {
    this.name = name;
    dorsal(dorsal);
    price(price);
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
