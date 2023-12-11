package com.elte.synchome.dto;

import com.elte.synchome.entity.User;
import com.elte.synchome.entity.house.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@ToString
public class HouseCreationRequest {
    private String userId;
    private List<Room> rooms = new ArrayList<>();
    private List<Kitchen> kitchens = new ArrayList<>();
    private List<Bathroom> bathrooms = new ArrayList<>();
    private List<Garage> garages = new ArrayList<>();
    private List<LivingRoom> livingRooms = new ArrayList<>();
    private List<Garden> gardens = new ArrayList<>();
    private List<Door> doors = new ArrayList<>();
    private Address address;
}
