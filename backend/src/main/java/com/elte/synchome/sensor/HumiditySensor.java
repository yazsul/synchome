package com.elte.synchome.sensor;

import com.elte.synchome.generator.HumiditySensorGenerator;
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
public class HumiditySensor implements Sensor {
    private static final Logger logger = LoggerFactory.getLogger(HumiditySensor.class);

    public HumiditySensor(){
        this.id = UUID.randomUUID().toString();
    }
    private String id;
    private List<Double> values;

    @Override
    public void readGeneratedData() {
        HumiditySensorGenerator generator = new HumiditySensorGenerator();
        this.values = generator.generateRandomReadings();
        logger.info("Humidity Sensor Data - Sensor ID: " + id + ", Values: " + values);
    }

    @Override
    public void readStoredData() {

    }
}
