package com.elte.synchome.sensor;

import com.elte.synchome.generator.TemperatureSensorGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class TemperatureSensor implements Sensor {
    private String sensorId;
    private List<Double> values;

    @Override
    public void readData() {
        TemperatureSensorGenerator generator = new TemperatureSensorGenerator();
        this.values = generator.generateRandomReadings();
        System.out.println("Temperature Sensor Data - Sensor ID: " + sensorId + ", Values: " + values);
    }
    // Implement temperature sensor-specific methods and properties
}