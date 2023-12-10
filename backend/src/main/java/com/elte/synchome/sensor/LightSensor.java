package com.elte.synchome.sensor;

import com.elte.synchome.generator.GasSensorGenerator;
import com.elte.synchome.generator.LightSensorGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
public class LightSensor implements Sensor {
    public LightSensor(){
        this.id = UUID.randomUUID().toString();
    }
    private String id;
    private List<Double> values;

    @Override
    public void readData() {
        LightSensorGenerator generator = new LightSensorGenerator();
        this.values = generator.generateRandomReadings();
        System.out.println("Light Sensor Data - Sensor ID: " + id + ", Values: " + values);
    }
}