package com.elte.synchome.sensor;

import com.elte.synchome.generator.WaterLeakSensorGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@Getter
@Setter
@ToString
public class WaterLeakSensor implements Sensor {
    private static final Logger logger = LoggerFactory.getLogger(WaterLeakSensor.class);

    public WaterLeakSensor(){
        this.id = UUID.randomUUID().toString();
    }

    private String id;
    private boolean isLeakingWater;

    @Override
    public void readGeneratedData() {
        WaterLeakSensorGenerator generator = new WaterLeakSensorGenerator();
        this.isLeakingWater = generator.generateRandomReadings();
        logger.info("Water Leak Sensor Data - Sensor ID: " + id + ", Is Water Leaking: " + isLeakingWater);
    }

    @Override
    public void readStoredData() {

    }
}
