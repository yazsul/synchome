package com.elte.synchome.entity;

import com.elte.synchome.entity.house.House;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Getter @Setter
@ToString
public class User {
    @Id
    private String id;
    private String username;
    private String PasswordHash;
    private String email;
    private List<String> housesIds = new ArrayList<>();
}