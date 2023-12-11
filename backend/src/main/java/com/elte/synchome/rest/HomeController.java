package com.elte.synchome.rest;

import com.elte.synchome.dto.HouseCreationRequest;
import com.elte.synchome.dto.DataFetchResponse;
import com.elte.synchome.entity.house.House;
import com.elte.synchome.service.HouseCreationService;

import com.elte.synchome.service.DataFetchService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.CredentialNotFoundException;

@RestController
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    private final HouseCreationService houseCreationService;
    private final DataFetchService dataFetchService;

    @Autowired
    public HomeController(HouseCreationService houseCreationService, DataFetchService dataFetchService) {
        this.houseCreationService = houseCreationService;
        this.dataFetchService = dataFetchService;
    }

    @PostMapping(value = "/create-house", consumes = {"application/json"}, produces = "application/json")
    public ResponseEntity<?> createHouse(@RequestBody HouseCreationRequest houseCreationRequest)  {
        logger.info("Creating house with the following houseCreationRequest: " + houseCreationRequest);

        try {
            return houseCreationService.createHouseToUser(houseCreationRequest);
        } catch (CredentialNotFoundException e) {
            String errorMessage = "{\"error\": \"" + e.getMessage() + "\"}";
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(errorMessage);
        }
    }

    @GetMapping("/fetch-all-sensors-data")
    public ResponseEntity<DataFetchResponse> fetchData() {
        logger.info("Fetching all data");
        return dataFetchService.fetchData();
    }

    @GetMapping("/fetch-all-sensors-data-no-generation")
    public ResponseEntity<DataFetchResponse> fetchDataWithoutGeneration() {
        logger.info("Fetching all data, with no data generation");
        return dataFetchService.fetchDataWithoutGeneration();
    }

    @GetMapping("/fetch-sensors-data-for-user/{id}")
    public ResponseEntity<DataFetchResponse> fetchDataForUser(@PathVariable final String id) {
        logger.info("Fetching data for user");
        return dataFetchService.fetchDataForUser(id);
    }

    @GetMapping("/fetch-sensors-data-no-generation-for-user/{id}")
    public ResponseEntity<DataFetchResponse> fetchDataWithoutGenerationForUser(@PathVariable final String id) {
        logger.info("Fetching all data, with no data generation for user with id: " + id);
        return dataFetchService.fetchDataWithoutGenerationForUser(id);
    }

    @PostMapping(value="/update-house/{houseId}", consumes = {"application/json"}, produces = "application/json")
    public ResponseEntity<?> updateHouse(@PathVariable final String houseId, @RequestBody HouseCreationRequest houseCreationRequest){
        return this.houseCreationService.updateHouse(houseId, houseCreationRequest);
    }
}