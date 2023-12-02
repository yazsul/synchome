package com.elte.synchome.entity.house;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
public class HouseBuilder {
    private static final Logger logger = LoggerFactory.getLogger(HouseBuilder.class);

    private List<HouseComponent> rooms = new ArrayList<>();
    private List<HouseComponent> kitchens = new ArrayList<>();
    private List<HouseComponent> bathrooms = new ArrayList<>();
    private List<HouseComponent> garages = new ArrayList<>();
    private List<HouseComponent> livingRooms = new ArrayList<>();
    private List<HouseComponent> gardens = new ArrayList<>();
    private List<HouseComponent> doors = new ArrayList<>();
    private Address address;

    public HouseBuilder addRoom(final Room room) {
        this.rooms.add(room);
        return this;
    }

    public HouseBuilder addManyRooms(final List<Room> rooms) {
        this.rooms.addAll(rooms);
        return this;
    }

    public HouseBuilder addKitchen(final Kitchen kitchen) {
        this.kitchens.add(kitchen);
        return this;
    }

    public HouseBuilder addManyKitchens(final List<Kitchen> kitchens) {
        this.kitchens.addAll(kitchens);
        return this;
    }

    public HouseBuilder addBathroom(final Bathroom bathroom) {
        this.bathrooms.add(bathroom);
        return this;
    }

    public HouseBuilder addManyBathrooms(final List<Bathroom> bathrooms) {
        this.bathrooms.addAll(bathrooms);
        return this;
    }

    public HouseBuilder addGarage(final Garage garage) {
        this.garages.add(garage);
        return this;
    }

    public HouseBuilder addManyGarages(final List<Garage> garages) {
        this.garages.addAll(garages);
        return this;
    }
    public HouseBuilder addLivingRoom(final LivingRoom livingRoom) {
        this.livingRooms.add(livingRoom);
        return this;
    }

    public HouseBuilder addManyLivingRooms(final List<LivingRoom> livingRooms) {
        this.livingRooms.addAll(livingRooms);
        return this;
    }
    public HouseBuilder addGarden(final Garden garden) {
        this.gardens.add(garden);
        return this;
    }

    public HouseBuilder addManyGardens(final List<Garden> gardens) {
        this.gardens.addAll(gardens);
        return this;
    }
    public HouseBuilder setAddress(final Address address) {
        this.address = address;
        return this;
    }

    public HouseBuilder addDoor(final Door door) {
        this.doors.add(door);
        return this;
    }

    public HouseBuilder addManyDoors(final List<Door> doors) {
        this.doors.addAll(doors);
        return this;
    }

    public House build() {
        return new House(this);
    }
}