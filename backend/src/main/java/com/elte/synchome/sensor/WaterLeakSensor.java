package com.elte.synchome.sensor;

import com.elte.synchome.generator.WaterLeakSensorGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WaterLeakSensor implements Sensor {
    private String sensorId;
    private boolean isLeakingWater;

    @Override
    public void readData() {
        WaterLeakSensorGenerator generator = new WaterLeakSensorGenerator();
        this.isLeakingWater = generator.generateRandomReadings();
        System.out.println("Water Leak Sensor Data - Sensor ID: " + sensorId + ", Is Water Leaking: " + isLeakingWater);
    }
    // Implement water leak sensor-specific methods and properties
}
