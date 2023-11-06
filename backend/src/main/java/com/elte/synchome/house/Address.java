package com.elte.synchome.house;

import lombok.*;

@Getter @Setter
@ToString
@RequiredArgsConstructor
public class Address {
  @NonNull private int streetNumber;
  @NonNull private String city;
  @NonNull private String country;
  @NonNull private int postcode;

  // add constraints to those values
}