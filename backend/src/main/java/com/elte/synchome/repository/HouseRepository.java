package com.elte.synchome.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.elte.synchome.entity.house.House;
public interface HouseRepository extends MongoRepository<House, String>{

}
