package com.elte.synchome.service;

import com.elte.synchome.dto.HouseCreationRequest;
import com.elte.synchome.entity.User;
import com.elte.synchome.entity.house.House;
import com.elte.synchome.entity.house.HouseBuilder;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.CredentialNotFoundException;

@Getter
@Setter
@Service
public class HouseCreationService {
    private static final Logger logger = LoggerFactory.getLogger(HouseCreationService.class);

    private final HouseService houseService;
    private final UserService userService;

    public HouseCreationService(final HouseService houseService, final UserService userService) {
        this.houseService = houseService;
        this.userService = userService;
    }

    private House createHouse(final HouseCreationRequest houseCreationRequest) {
        logger.info("Creating house with the following houseCreationRequest: " + houseCreationRequest);

        var rooms = houseCreationRequest.getRooms();
        var kitchens = houseCreationRequest.getKitchens();
        var bathrooms = houseCreationRequest.getBathrooms();
        var garages = houseCreationRequest.getGarages();
        var livingRooms = houseCreationRequest.getLivingRooms();
        var gardens = houseCreationRequest.getGardens();
        var doors = houseCreationRequest.getDoors();
        var address = houseCreationRequest.getAddress();

        return new HouseBuilder()
                .addManyRooms(rooms)
                .addManyKitchens(kitchens)
                .addManyBathrooms(bathrooms)
                .addManyGarages(garages)
                .addManyLivingRooms(livingRooms)
                .addManyGardens(gardens)
                .addManyDoors(doors)
                .setAddress(address)
                .build();
    }

    @Transactional
    public ResponseEntity<House> createHouseToUser(final HouseCreationRequest houseCreationRequest) throws CredentialNotFoundException {
        logger.info("Creating a house with the following houseCreationRequest: " + houseCreationRequest);

        House createdHouse = this.createHouse(houseCreationRequest);

        String userId = houseCreationRequest.getUserId();
        User user = this.userService.getUserById(userId);

        if (user != null) {
            House savedHouse = this.houseService.saveHouse(createdHouse);

            user.getHousesIds().add(savedHouse.getId());
            this.userService.updateUser(userId, user);

            savedHouse.getUsersIds().add(userId);
            this.houseService.saveHouse(savedHouse);

            return ResponseEntity.status(HttpStatus.OK).body(savedHouse);
        } else {
            logger.info("House creation operation unsuccessful! User with ID " + userId + " was not found!");
            throw new CredentialNotFoundException("User not found");
        }
    }

    public ResponseEntity<String> updateHouse(String id, HouseCreationRequest houseCreationRequest) {
        House existingHouse = this.houseService.getHouseById(id);

        if (existingHouse != null) {
            existingHouse.setAddress(houseCreationRequest.getAddress());
            this.houseService.saveHouse(existingHouse);
        }
        return ResponseEntity.ok().build();
    }
}
