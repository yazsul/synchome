package com.elte.synchome.sensor;

import com.elte.synchome.generator.GasSensorGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter
@ToString
public class GasSensor implements Sensor {
  private String sensorId;
  private List<Double> values;

  @Override
  public void readData() {
    GasSensorGenerator generator = new GasSensorGenerator();
    this.values = generator.generateRandomReadings();
    System.out.println("Gas Sensor Data - Sensor ID: " + sensorId + ", Values: " + values);
  }
//   Implement gas sensor-specific methods and properties
}