package com.elte.synchome.sensor;

import com.elte.synchome.generator.TemperatureSensorGenerator;
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
public class TemperatureSensor implements Sensor {
    private static final Logger logger = LoggerFactory.getLogger(TemperatureSensor.class);

    public TemperatureSensor(){
        this.id = UUID.randomUUID().toString();
    }
    private String id;
    private List<Double> values;

    @Override
    public void readGeneratedData() {
        TemperatureSensorGenerator generator = new TemperatureSensorGenerator();
        this.values = generator.generateRandomReadings();
        logger.info("Temperature Sensor Data - Sensor ID: " + id + ", Values: " + values);
    }

    @Override
    public void readStoredData() {

    }
}