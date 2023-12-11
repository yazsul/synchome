package com.elte.synchome.generator;

import java.util.Random;

public class WaterLeakSensorGenerator {
  private final Random random = new Random();
  public boolean generateRandomReadings() {
      return random.nextBoolean();
  }
}
