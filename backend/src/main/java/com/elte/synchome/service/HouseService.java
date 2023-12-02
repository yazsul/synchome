package com.elte.synchome.service;

import com.elte.synchome.entity.house.House;
import com.elte.synchome.repository.HouseRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
public class HouseService {
    private final HouseRepository houseRepository;

    public HouseService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    public void saveHouse(final House house) {
        this.houseRepository.save(house);
    }
    public void removeHouseById(final String id) {
        this.houseRepository.deleteById(id);
    }

    public void removeAllHouses() {
        this.houseRepository.deleteAll();
    }

    public House getHouseById(final String id) {
        return this.houseRepository.findById(id).orElse(null);
    }

    public List<House> getAllHouses() {
        return this.houseRepository.findAll();
    }

}