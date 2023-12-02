package com.elte.synchome.sensor;

import com.elte.synchome.generator.WindowSensorGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class WindowSensor implements Sensor {

    public WindowSensor(){
        this.id = UUID.randomUUID().toString();
    }

    private String id;
    private boolean isWindowOpen;

    @Override
    public void readData() {
        WindowSensorGenerator generator = new WindowSensorGenerator();
        this.isWindowOpen = generator.generateRandomReadings();
        System.out.println("Window Sensor Data - Sensor ID: " + id + ", Is Window Open: " + isWindowOpen);
    }
}
