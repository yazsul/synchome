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

    public House saveHouse(final House house) {
        return this.houseRepository.save(house);
    }
    public void removeHouseById(final String id) {
        this.houseRepository.deleteById(id);
    }
    public void removeHouse(final House house) {
        this.houseRepository.delete(house);
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