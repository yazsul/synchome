package com.elte.synchome.service;

import com.elte.synchome.dto.DataFetchResponse;
import com.elte.synchome.entity.User;
import com.elte.synchome.entity.house.House;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Service
public class DataFetchService {
    private static final Logger logger = LoggerFactory.getLogger(DataFetchService.class);

    private final HouseService houseService;
    private final UserService userService;

    public DataFetchService(HouseService houseService, UserService userService) {
        this.houseService = houseService;
        this.userService = userService;
    }

    public ResponseEntity<DataFetchResponse> fetchData() {
        final List<House> houses = this.houseService.getAllHouses();

        logger.info("Fetching data for all houses: " + houses);
        if (houses.isEmpty()) {
            logger.error("No houses stored to fetch data from!");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(readDataAndGetResponse(houses));
    }

    public ResponseEntity<DataFetchResponse> fetchDataWithoutGeneration() {
        final List<House> houses = this.houseService.getAllHouses();

        logger.info("Fetching data for all houses: " + houses);
        if (houses.isEmpty()) {
            logger.error("No houses stored to fetch data from!");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(readDataWithoutGenerationAndGetResponse(houses));
    }

    public ResponseEntity<DataFetchResponse> fetchDataForUser(final String userId) {
        User user = this.userService.getUserById(userId);

        if(user == null){
            logger.error("User not found!");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        List<House> houses = user.getHousesIds().stream()
                .map(houseService::getHouseById)
                .collect(Collectors.toList());

        logger.info("Fetching data for houses for user: " + user);
        if (houses.isEmpty()) {
            logger.error("No houses stored to fetch data from!");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(readDataAndGetResponse(houses));
    }

    public ResponseEntity<DataFetchResponse> fetchDataWithoutGenerationForUser(final String userId) {
        User user = this.userService.getUserById(userId);

        if(user == null){
            logger.error("User not found!");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        List<House> houses = user.getHousesIds().stream()
                .map(houseService::getHouseById)
                .collect(Collectors.toList());

        logger.info("Fetching data for houses for user: " + user);
        if (houses.isEmpty()) {
            logger.error("No houses stored to fetch data from!");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(readDataWithoutGenerationAndGetResponse(houses));
    }

    private DataFetchResponse readDataAndGetResponse(final List<House> houses) {
        DataFetchResponse response = new DataFetchResponse();
        houses.forEach(house -> {
            house.readGeneratedSensorsData();
            response.getHouses().add(house);
        });
        return response;
    }

    private DataFetchResponse readDataWithoutGenerationAndGetResponse(final List<House> houses) {
        DataFetchResponse response = new DataFetchResponse();
        houses.forEach(house -> {
            house.readStoredSensorsData();
            response.getHouses().add(house);
        });
        return response;
    }
}