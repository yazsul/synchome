package com.elte.synchome.house;

import com.elte.synchome.sensor.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
public class Door implements HouseComponent {
    private String material;
    private DoorSensor doorSensor;

    @Override
    public void readSensorsData() {
        doorSensor.readData();
    }
}
