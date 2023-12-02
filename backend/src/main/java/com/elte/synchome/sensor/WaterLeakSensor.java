package com.elte.synchome.sensor;

import com.elte.synchome.generator.WaterLeakSensorGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class WaterLeakSensor implements Sensor {

    public WaterLeakSensor(){
        this.id = UUID.randomUUID().toString();
    }

    private String id;
    private boolean isLeakingWater;

    @Override
    public void readData() {
        WaterLeakSensorGenerator generator = new WaterLeakSensorGenerator();
        this.isLeakingWater = generator.generateRandomReadings();
        System.out.println("Water Leak Sensor Data - Sensor ID: " + id + ", Is Water Leaking: " + isLeakingWater);
    }
}
