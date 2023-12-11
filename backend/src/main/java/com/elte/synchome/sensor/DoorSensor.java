package com.elte.synchome.sensor;

import com.elte.synchome.generator.DoorSensorGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@Getter @Setter
@ToString
public class DoorSensor implements Sensor {
    private static final Logger logger = LoggerFactory.getLogger(DoorSensor.class);

    public DoorSensor(){
        this.id = UUID.randomUUID().toString();
    }
    private String id;
    private boolean isDoorOpen;

  @Override
  public void readGeneratedData() {
    DoorSensorGenerator generator = new DoorSensorGenerator();
    this.isDoorOpen = generator.generateRandomReadings();
      logger.info("Door Sensor Data - Sensor ID: " + id + ", Is Door Open: " + isDoorOpen);
   }

    @Override
    public void readStoredData() {

    }
}