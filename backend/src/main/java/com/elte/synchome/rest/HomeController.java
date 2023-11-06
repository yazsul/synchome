package com.elte.synchome.rest;

import com.elte.synchome.dto.HouseCreationRequest;
import com.elte.synchome.dto.DataFetchResponse;
import com.elte.synchome.house.House;
import com.elte.synchome.service.HouseService;

import com.elte.synchome.service.DataFetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    private final HouseService houseService;
    private final DataFetchService dataFetchService;


    @Autowired
    public HomeController(HouseService houseService, DataFetchService dataFetchService) {
        this.houseService = houseService;
        this.dataFetchService = dataFetchService;
    }

    @PostMapping(value = "/create-house", consumes = {"application/json"}, produces = "application/json")
    public House createHouse(@RequestBody HouseCreationRequest houseCreationRequest) {
        logger.info("createHouse is called and the following parameter is passed: " + houseCreationRequest);
        return houseService.createHouse(houseCreationRequest);
    }

    @GetMapping("/fetch-sensors-data")
    public DataFetchResponse fetchData() {
        logger.info("fetchData is called");
        DataFetchResponse response = dataFetchService.fetchData(houseService.getHouses());
        return response;
    }
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello, World!";
    }
}