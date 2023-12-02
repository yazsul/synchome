package com.elte.synchome.dto;

import com.elte.synchome.entity.house.House;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class DataFetchResponse {
    List<House> houses = new ArrayList<>();
}