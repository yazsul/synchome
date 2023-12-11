package com.elte.synchome.entity.house;

import com.elte.synchome.sensor.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter @Setter
@ToString
public class Garage implements HouseComponent {

  public Garage() {
    this.id = UUID.randomUUID().toString();
  }
  private String id;
  private GasSensor gasSensor;
  private HumiditySensor humiditySensor;
  private LightSensor lightSensor;
  private TemperatureSensor temperatureSensor;
  private VentilationSensor ventilationSensor;
  private WaterLeakSensor waterLeakSensor;
  private List<Window> windows;
  private List<Door> doors;

  @Override
  public void readGeneratedSensorsData() {
    gasSensor.readGeneratedData();
    humiditySensor.readGeneratedData();
    lightSensor.readGeneratedData();
    temperatureSensor.readGeneratedData();
    ventilationSensor.readGeneratedData();
    waterLeakSensor.readGeneratedData();
    windows.forEach(Window::readGeneratedSensorsData);
    doors.forEach(Door::readGeneratedSensorsData);
  }

  @Override
  public void readStoredSensorsData() {
    gasSensor.readStoredData();
    humiditySensor.readStoredData();
    lightSensor.readStoredData();
    temperatureSensor.readStoredData();
    ventilationSensor.readStoredData();
    waterLeakSensor.readStoredData();
    windows.forEach(Window::readStoredSensorsData);
    doors.forEach(Door::readStoredSensorsData);
  }
}
