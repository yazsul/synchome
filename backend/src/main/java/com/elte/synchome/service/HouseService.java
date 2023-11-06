package com.elte.synchome.service;

import com.elte.synchome.dto.HouseCreationRequest;
import com.elte.synchome.house.House;
import com.elte.synchome.house.HouseBuilder;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Service
public class HouseService {
    private static final Logger logger = LoggerFactory.getLogger(HouseService.class);
    private List<House> houses = new ArrayList<>();

    public House createHouse(HouseCreationRequest houseCreationRequest) {
        logger.info("createHouse is called and the following parameter is passed: " + houseCreationRequest);

        var rooms = houseCreationRequest.getRooms();
        var kitchens = houseCreationRequest.getKitchens();
        var bathrooms = houseCreationRequest.getBathrooms();
        var garages = houseCreationRequest.getGarages();
        var livingRooms = houseCreationRequest.getLivingRooms();
        var gardens = houseCreationRequest.getGardens();
        var doors = houseCreationRequest.getDoors();
        var address = houseCreationRequest.getAddress();

        House house = new HouseBuilder()
                .addManyRooms(rooms)
                .addManyKitchens(kitchens)
                .addManyBathrooms(bathrooms)
                .addManyGarages(garages)
                .addManyLivingRooms(livingRooms)
                .addManyGardens(gardens)
                .addManyDoors(doors)
                .setAddress(address)
                .build();

        houses.add(house);
        return house;
    }
}