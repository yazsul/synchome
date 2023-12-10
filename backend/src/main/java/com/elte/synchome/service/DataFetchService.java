package com.elte.synchome.service;

import com.elte.synchome.dto.DataFetchResponse;
import com.elte.synchome.entity.house.House;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
public class DataFetchService {
    private static final Logger logger = LoggerFactory.getLogger(DataFetchService.class);

    private final HouseService houseService;

    public DataFetchService(HouseService houseService) {
        this.houseService = houseService;
    }

    public DataFetchResponse fetchData() {
        final List<House> houses = this.houseService.getAllHouses();

        logger.info("fetchData is called and the following parameter is passed: " + houses);
        if (houses == null) {
            logger.error("Null value is passed therefore an empty response is returned");
            return new DataFetchResponse();
        }
        return ReadDataAndGetResponse(houses);
    }

    private DataFetchResponse ReadDataAndGetResponse(final List<House> houses) {
        DataFetchResponse response = new DataFetchResponse();
        houses.forEach(house -> {
            house.readSensorsData();
            response.getHouses().add(house);
        });
        return response;
    }
}