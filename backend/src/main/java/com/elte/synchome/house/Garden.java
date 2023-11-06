package com.elte.synchome.house;

import com.elte.synchome.sensor.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter
@ToString
public class Garden implements HouseComponent{
  private GasSensor gasSensor;
  private HumiditySensor humiditySensor;
  private LightSensor lightSensor;
  private TemperatureSensor temperatureSensor;
  private List<Door> doors;

  @Override
  public void readSensorsData() {
    gasSensor.readData();
    humiditySensor.readData();
    lightSensor.readData();
    temperatureSensor.readData();
    doors.forEach(door -> door.readSensorsData());
  }
}
