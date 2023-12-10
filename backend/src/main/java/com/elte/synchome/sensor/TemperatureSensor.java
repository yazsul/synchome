package com.elte.synchome.sensor;

import com.elte.synchome.generator.TemperatureSensorGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
public class TemperatureSensor implements Sensor {

    public TemperatureSensor(){
        this.id = UUID.randomUUID().toString();
    }
    private String id;
    private List<Double> values;

    @Override
    public void readData() {
        TemperatureSensorGenerator generator = new TemperatureSensorGenerator();
        this.values = generator.generateRandomReadings();
        System.out.println("Temperature Sensor Data - Sensor ID: " + id + ", Values: " + values);
    }
}