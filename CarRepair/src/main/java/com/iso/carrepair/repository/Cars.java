package com.iso.carrepair.repository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Component
public class Cars {
    private List<Car> cars = new ArrayList<>();
    public List<Car> getCars(){
        return cars;
    }
    public void setCars (List<Car> cars){
        this.cars = cars;
    }
    public void addCar(Car car){
        cars.add(car);
        System.out.println("Nowe auto dodane: " + car.toString());
    }
}
