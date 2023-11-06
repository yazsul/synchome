package com.elte.synchome.sensor;

import com.elte.synchome.generator.DoorSensorGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class DoorSensor implements Sensor {

    private String sensorId;
    private boolean isDoorOpen;

  @Override
  public void readData() {
    DoorSensorGenerator generator = new DoorSensorGenerator();
    this.isDoorOpen = generator.generateRandomReadings();
    System.out.println("Door Sensor Data - Sensor ID: " + sensorId + ", Is Door Open: " + isDoorOpen);
   }

  // Implement door sensor-specific methods and properties
}