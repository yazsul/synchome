package com.elte.synchome.sensor;

import com.elte.synchome.generator.GasSensorGenerator;
import com.elte.synchome.generator.VentilationSensorGenerator;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
public class VentilationSensor implements Sensor {
    private static final Logger logger = LoggerFactory.getLogger(VentilationSensor.class);

    public VentilationSensor(){
        this.id = UUID.randomUUID().toString();
    }

    private String id;
    private List<Double> values;

    @Override
    public void readGeneratedData() {
        VentilationSensorGenerator generator = new VentilationSensorGenerator();
        this.values = generator.generateRandomReadings();
        logger.info("Ventilation Sensor Data - Sensor ID: " + id + ", Values: " + values);
    }

    @Override
    public void readStoredData() {

    }
}
 