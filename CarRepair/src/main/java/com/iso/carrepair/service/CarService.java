package com.iso.carrepair.service;

import com.google.gson.Gson;
import com.iso.carrepair.exception.CarNotFoundException;
import com.iso.carrepair.repository.Car;
import com.iso.carrepair.repository.Cars;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final Gson gson = new Gson();
    Reader reader = Files.newBufferedReader(Paths.get("CarRepair/src/main/java/com/iso/carrepair/database/car.json"));
    private FileService fileService;
    private List<Car> carListToBeFix;
    private List<Car> carListFixed;

    public CarService( FileService fileService) throws IOException {
        this.fileService = fileService;
        this.carListToBeFix = fileService.readCarFromJsonFileToBeFixed().getCars();
    }

    public List<Car> getCarToFixList(){
        return carListToBeFix.stream()
                .filter(car -> car.isStatus()==false)
                .sorted(Comparator.comparing(Car::getAcceptanceForService))
                .collect(Collectors.toList());
    }
    public List<Car> getCarFixedList() {
        return carListFixed.stream()
                .sorted(Comparator.comparing(Car::getRepairDate).reversed())
                .collect(Collectors.toList());
    }

    public void addCarToBeFix(Car car){
        carListToBeFix.add(car);
    }
    public void addCarFixed(Car car){
        carListFixed.add(car);
    }
    public void saveCarToJsonToBeFixed() throws IOException {
        Cars carsCopy = new Cars();
        for (Car car : carListToBeFix){
            carsCopy.addCar(car);
        }
        fileService.writeCarToJsonToBeFixed(carsCopy);
    }
    public void saveCarToJsonFixed() throws IOException {
        Cars carsCopy = new Cars();
        for (Car car : carListFixed){
            carsCopy.addCar(car);
        }
        fileService.writeCarToJsonFixed(carsCopy);
    }
    public List<Car> findCarByPlateList(String test){
        return carListToBeFix.stream()
                .filter(car -> car.getLicensePlate().equals(test))
                .filter(car -> car.isStatus() == false)
                .collect(Collectors.toList());
    }
    public Car findCarByPlate(String plate){
        return carListToBeFix.stream()
                .filter(car -> car.getLicensePlate().equals(plate))
                .findFirst()
                .orElseThrow(()->new CarNotFoundException("Not found"));
    }
    public void changeStatus (String plate){
        Car foundCar = findCarByPlate(plate);
        foundCar.setStatus(true);
        foundCar.setRepairDate(LocalDate.now().toString());
        carListFixed.add(foundCar);
        carListToBeFix.remove(foundCar);
    }

}

