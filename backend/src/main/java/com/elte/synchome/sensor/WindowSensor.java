package com.elte.synchome.sensor;

import com.elte.synchome.generator.WindowSensorGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@Getter
@Setter
@ToString
public class WindowSensor implements Sensor {
    private static final Logger logger = LoggerFactory.getLogger(WindowSensor.class);

    public WindowSensor(){
        this.id = UUID.randomUUID().toString();
    }

    private String id;
    private boolean isWindowOpen;

    @Override
    public void readGeneratedData() {
        WindowSensorGenerator generator = new WindowSensorGenerator();
        this.isWindowOpen = generator.generateRandomReadings();
        logger.info("Window Sensor Data - Sensor ID: " + id + ", Is Window Open: " + isWindowOpen);
    }

    @Override
    public void readStoredData() {

    }
}
