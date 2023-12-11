package com.elte.synchome.sensor;

import com.elte.synchome.generator.GasSensorGenerator;
import com.elte.synchome.generator.LightSensorGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
public class LightSensor implements Sensor {
    private static final Logger logger = LoggerFactory.getLogger(LightSensor.class);

    public LightSensor(){
        this.id = UUID.randomUUID().toString();
    }
    private String id;
    private List<Double> values;

    @Override
    public void readGeneratedData() {
        LightSensorGenerator generator = new LightSensorGenerator();
        this.values = generator.generateRandomReadings();
        logger.info("Light Sensor Data - Sensor ID: " + id + ", Values: " + values);
    }

    @Override
    public void readStoredData() {

    }
}