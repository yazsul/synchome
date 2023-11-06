package com.elte.synchome.sensor;

import com.elte.synchome.generator.HumiditySensorGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class HumiditySensor implements Sensor {
    private String sensorId;
    private List<Double> values;

    @Override
    public void readData() {
        HumiditySensorGenerator generator = new HumiditySensorGenerator();
        this.values = generator.generateRandomReadings();
        System.out.println("Humidity Sensor Data - Sensor ID: " + sensorId + ", Values: " + values);
    }
    // Implement humidity sensor-specific methods and properties
}
