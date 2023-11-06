package com.elte.synchome.generator;

import java.util.Random;

public class WindowSensorGenerator {
  private Random random = new Random();
  public boolean generateRandomReadings() {
      return random.nextBoolean();
  }
}
