package com.elte.synchome.service;

import com.elte.synchome.dto.HouseCreationRequest;
import com.elte.synchome.entity.house.House;
import com.elte.synchome.entity.house.HouseBuilder;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class HouseCreationService {
    private static final Logger logger = LoggerFactory.getLogger(HouseCreationService.class);
    private final HouseService houseService;

    public HouseCreationService(HouseService houseService) {
        this.houseService = houseService;
    }

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

        this.houseService.saveHouse(house);

        return house;
    }
}
