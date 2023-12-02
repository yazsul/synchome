package com.elte.synchome.entity.house;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "houses")
public class House {
    @Id
    private String id;
    private List<HouseComponent> rooms;
    private List<HouseComponent> kitchens;
    private List<HouseComponent> bathrooms;
    private List<HouseComponent> garages;
    private List<HouseComponent> livingRooms;
    private List<HouseComponent> gardens;
    private List<HouseComponent> doors;
    private Address address;

    public House() {
    }

    public House(final HouseBuilder houseBuilder) {
        this.rooms = houseBuilder.getRooms();
        this.kitchens = houseBuilder.getKitchens();
        this.bathrooms = houseBuilder.getBathrooms();
        this.garages = houseBuilder.getGarages();
        this.livingRooms = houseBuilder.getLivingRooms();
        this.gardens = houseBuilder.getGardens();
        this.doors = houseBuilder.getDoors();
        this.address = houseBuilder.getAddress();
    }

    public void readSensorsData() {
        new ArrayList<>(Arrays.asList(
                rooms, kitchens, bathrooms,
                garages, livingRooms, gardens,
                doors))
                .forEach(houseComponents -> {
                    houseComponents.forEach(houseComponent -> {
                        houseComponent.readSensorsData();
                    });
                });
    }
}
