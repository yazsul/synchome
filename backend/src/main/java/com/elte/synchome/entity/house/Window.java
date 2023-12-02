package com.elte.synchome.entity.house;

import com.elte.synchome.sensor.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter @Setter
@ToString
public class Window implements HouseComponent {

  public Window() {
    this.id = UUID.randomUUID().toString();
  }

  private String id;
  private String material;
  private WindowSensor windowSensor;

  @Override
  public void readSensorsData() {
  windowSensor.readData();
  }
}
