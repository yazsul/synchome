package com.elte.synchome.generator;

import java.util.Random;

public class DoorSensorGenerator {
  private Random random = new Random();
  public boolean generateRandomReadings() {
      return random.nextBoolean();
  }
}
