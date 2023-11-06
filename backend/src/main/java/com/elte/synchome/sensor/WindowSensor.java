package com.elte.synchome.sensor;

import com.elte.synchome.generator.WindowSensorGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WindowSensor implements Sensor {
    private String sensorId;
    private boolean isWindowOpen;

    @Override
    public void readData() {
        WindowSensorGenerator generator = new WindowSensorGenerator();
        this.isWindowOpen = generator.generateRandomReadings();
        System.out.println("Window Sensor Data - Sensor ID: " + sensorId + ", Is Window Open: " + isWindowOpen);
    }
    // Implement window sensor-specific methods and properties
}
