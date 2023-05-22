package com.iso.carrepair.service;

import com.iso.carrepair.exception.CarNotFoundException;
import com.iso.carrepair.repository.Car;
import com.iso.carrepair.repository.CarColor;
import com.iso.carrepair.repository.Cars;
import com.iso.carrepair.repository.FileService;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.iso.carrepair.repository.FileService.gson;

public class CarService {
    private List<Car> carList;
    private FileService fileService;

    public CarService( FileService fileService) {
        this.fileService = fileService;
        carList = new ArrayList<Car>(fileService.readCarFromJsonFile().getCars().values());
    }

    public List<Car> getCarToFixList(){
        return carList.stream()
                .filter(car -> car.isStatus()==false)
                .collect(Collectors.toList());
    }
    public List<Car> getCarFixedList() {
        return carList.stream()
                .filter(car -> car.isStatus() == true)
                .collect(Collectors.toList());
    }

    public void addCar (Car car){
        carList.add(car);
    }

    public void removeCarById(long id){
    Car foundCar = findCarById(id);
    carList.remove(foundCar);
    }

    public Car findCarById(long id) {
        return carList.stream()
                .filter(car -> car.getId()==id)
                .findFirst()
                .orElseThrow(() -> new CarNotFoundException("Not found car with ID: %s".formatted(id)));
    }
    public void saveCarToJson() throws IOException {
       Car car = new Car(1, "DWR 1565C", "Volvo",CarColor.BRĄZOWY,2020, LocalDate.now(),LocalDate.now().plusWeeks(1l),false);
       gson.toJson(car,new FileWriter("/home/jakub/Desktop/Projekt/jjdzr8-poprawka/CarRepair/src/main/java/com/iso/carrepair/database/car.json"));
    }
    public void mainTest(){

    }
}
