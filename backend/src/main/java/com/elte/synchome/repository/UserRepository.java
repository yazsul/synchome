package com.elte.synchome.repository;

import com.elte.synchome.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>{
    User findByUsername(String username);
    User findByEmail(String email);

}
