package com.iso.carrepair.service;

import com.google.gson.Gson;
import com.iso.carrepair.exception.CarNotFoundException;
import com.iso.carrepair.repository.Car;
import com.iso.carrepair.repository.CarColor;
import com.iso.carrepair.repository.Cars;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final Gson gson = new Gson();
    private FileService fileService;
    private List<Car> carList;
//    public List<Car> carsForTest(){
//        this.carList = new ArrayList<>();
//        carList.add(new Car("DWR 1565C", "Volvo", CarColor.BRĄZOWY, 2010,LocalDate.now().toString(),false));
//        carList.add(new Car("DWR 1565C", "Volvo", CarColor.BRĄZOWY, 2010,LocalDate.now().toString(),false));
//        return carList;
//    }

    public CarService( FileService fileService) {
        this.fileService = fileService;
        this.carList = new ArrayList<>();
        carList.add(new Car("DWR 1565C", "Volvo", CarColor.BRĄZOWY, 2010,LocalDate.now().toString(),false));
        carList.add(new Car("DWR 1565C", "Volvo", CarColor.BRĄZOWY, 2010,LocalDate.now().toString(),false));
    }
    public List<Car> allCars(){
        return carList;
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
    public void saveCarToJson() throws IOException {
        Cars carsCopy = new Cars();
        for (Car car : carList){
            carsCopy.addCar(car);
        }
        fileService.writeCarToJson(carsCopy);
    }

    public Car findCarById(long id) {
        return carList.stream()
                .filter(car -> car.getId()==id)
                .findFirst()
                .orElseThrow(() -> new CarNotFoundException("Not found car with ID: %s".formatted(id)));
    }
}
