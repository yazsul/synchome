package com.elte.synchome.rest;

import com.elte.synchome.dto.HouseCreationRequest;
import com.elte.synchome.dto.DataFetchResponse;
import com.elte.synchome.entity.house.House;
import com.elte.synchome.service.HouseCreationService;

import com.elte.synchome.service.DataFetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public House createHouse(@RequestBody HouseCreationRequest houseCreationRequest) {
        logger.info("createHouse is called and the following parameter is passed: " + houseCreationRequest);
        return houseCreationService.createHouse(houseCreationRequest);
    }

    @GetMapping("/fetch-sensors-data")
    public DataFetchResponse fetchData() {
        logger.info("fetchData is called");
        DataFetchResponse response = dataFetchService.fetchData();
        return response;
    }
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello, World!";
    }
}