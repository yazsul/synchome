package com.elte.synchome.house;

import com.elte.synchome.sensor.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class Window implements HouseComponent {
  private WindowSensor windowSensor;

  @Override
  public void readSensorsData() {
  windowSensor.readData();
  }
}
