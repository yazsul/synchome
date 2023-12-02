package com.elte.synchome.sensor;

import com.elte.synchome.generator.HumiditySensorGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
public class HumiditySensor implements Sensor {
    public HumiditySensor(){
        this.id = UUID.randomUUID().toString();
    }
    private String id;
    private List<Double> values;

    @Override
    public void readData() {
        HumiditySensorGenerator generator = new HumiditySensorGenerator();
        this.values = generator.generateRandomReadings();
        System.out.println("Humidity Sensor Data - Sensor ID: " + id + ", Values: " + values);
    }
}
