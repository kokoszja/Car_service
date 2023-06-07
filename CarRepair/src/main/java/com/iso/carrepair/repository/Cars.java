package com.iso.carrepair.repository;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
@Component
public class Cars {
    private List<Car> cars = new ArrayList<>();
    public List<Car> getCars(){
        return cars;
    }
    public void addCar(Car car){
        cars.add(car);
        System.out.println("Nowe auto dodane: " + car.toString());
    }
}
