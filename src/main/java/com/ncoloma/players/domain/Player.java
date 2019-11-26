package com.ncoloma.players.domain;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Player {
  private UUID id;
  private String name;
  private int dorsal;
  private double price;

  public Player(UUID id, String name, int dorsal, double price){
    this.id = id;
    this.name = name;
    this.dorsal = dorsal;
    price(price);
  }

  public void changeDorsal(int dorsal) {
    this.dorsal = dorsal;
  }

  private void price(double price) {
    if (price <= 0) {
      throw new RuntimeException("Price must be above 0 euros");
    }
    this.price = price;
  }
}
