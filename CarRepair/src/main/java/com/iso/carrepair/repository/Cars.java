package com.iso.carrepair.repository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Component
public class Cars {
    private Map<Long, Car> cars = new TreeMap<>();
    public Map<Long, Car> getCars(){
        return cars;
    }
    public void simpleAddCar(Car car){
        cars.put(car.getId(), car);
    }
}
