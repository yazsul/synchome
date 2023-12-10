package com.elte.synchome.entity.house;

import lombok.*;

import java.util.UUID;

@Getter @Setter
@ToString
@RequiredArgsConstructor
public class Address {
  public Address() {
    this.id = UUID.randomUUID().toString();
  }
  private String id;
  @NonNull private int streetNumber;
  @NonNull private String city;
  @NonNull private String country;
  @NonNull private int postcode;

  // add constraints to those values
}