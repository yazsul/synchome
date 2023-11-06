package com.elte.synchome.sensor;

import com.elte.synchome.generator.GasSensorGenerator;
import com.elte.synchome.generator.VentilationSensorGenerator;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class VentilationSensor implements Sensor {
    private String sensorId;
    private List<Double> values;

    @Override
    public void readData() {
        VentilationSensorGenerator generator = new VentilationSensorGenerator();
        this.values = generator.generateRandomReadings();
        System.out.println("Ventilation Sensor Data - Sensor ID: " + sensorId + ", Values: " + values);
    }
    // Implement ventilation sensor-specific methods and properties
}
 