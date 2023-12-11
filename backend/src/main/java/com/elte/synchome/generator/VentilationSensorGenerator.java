package com.elte.synchome.generator;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class VentilationSensorGenerator {
    // values range for residential ventilation 50-100[CFM]
    private static final double MIN_READING = 50.0;
    private static final double MAX_READING = 100.0;
    private static final int NUM_OF_READINGS = 1;
    private final Random random = new Random();
    public List<Double> generateRandomReadings() {
        return random.doubles(NUM_OF_READINGS, MIN_READING, MAX_READING)
                .boxed()
                .collect(Collectors.toList());
    }
}
