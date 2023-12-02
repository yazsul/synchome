package com.elte.synchome.sensor;

import com.elte.synchome.generator.GasSensorGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter @Setter
@ToString
public class GasSensor implements Sensor {
  public GasSensor(){
    this.id = UUID.randomUUID().toString();
  }
  private String id;
  private List<Double> values;

  @Override
  public void readData() {
    GasSensorGenerator generator = new GasSensorGenerator();
    this.values = generator.generateRandomReadings();
    System.out.println("Gas Sensor Data - Sensor ID: " + id + ", Values: " + values);
  }
}
