package com.elte.synchome.sensor;

import com.elte.synchome.generator.DoorSensorGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter @Setter
@ToString
public class DoorSensor implements Sensor {

    public DoorSensor(){
        this.id = UUID.randomUUID().toString();
    }
    private String id;
    private boolean isDoorOpen;

  @Override
  public void readData() {
    DoorSensorGenerator generator = new DoorSensorGenerator();
    this.isDoorOpen = generator.generateRandomReadings();
    System.out.println("Door Sensor Data - Sensor ID: " + id + ", Is Door Open: " + isDoorOpen);
   }
}