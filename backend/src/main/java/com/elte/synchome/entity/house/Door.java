package com.elte.synchome.entity.house;

import com.elte.synchome.sensor.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class Door implements HouseComponent {
    public Door() {
        this.id = UUID.randomUUID().toString();
    }
    private String id;
    private String material;
    private DoorSensor doorSensor;

    @Override
    public void readGeneratedSensorsData() {
        doorSensor.readGeneratedData();
    }

    @Override
    public void readStoredSensorsData() {
        doorSensor.readStoredData();
    }
}
