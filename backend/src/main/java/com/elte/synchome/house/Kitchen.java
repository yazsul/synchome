package com.elte.synchome.house;

import com.elte.synchome.sensor.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter
@ToString
public class Kitchen implements HouseComponent{
  private GasSensor gasSensor;
  private HumiditySensor humiditySensor;
  private LightSensor lightSensor;
  private TemperatureSensor temperatureSensor;
  private VentilationSensor ventilationSensor;
  private WaterLeakSensor waterLeakSensor;
  private List<Window> windows;
  private List<Door> doors;

  @Override
  public void readSensorsData() {
    gasSensor.readData();
    humiditySensor.readData();
    lightSensor.readData();
    temperatureSensor.readData();
    ventilationSensor.readData();
    waterLeakSensor.readData();
    windows.forEach(window -> window.readSensorsData());
    doors.forEach(door -> door.readSensorsData());
  }

}
