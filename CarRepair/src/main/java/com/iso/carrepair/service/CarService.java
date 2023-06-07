package com.iso.carrepair.service;
import com.google.gson.Gson;
import com.iso.carrepair.exception.CarNotFoundException;
import com.iso.carrepair.repository.Car;
import com.iso.carrepair.repository.Cars;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final Gson gson = new Gson();
    private FileService fileService;
    private List<Car> carList;
    public CarService( FileService fileService) throws IOException {
        this.fileService = fileService;
        this.carList = fileService.readCarFromJsonFile().getCars();
    }
    public List<Car> getCarToFixList(){
        return carList.stream()
                .filter(car -> car.isStatus()==false)
                .sorted(Comparator.comparing(Car::getAcceptanceForService))
                .collect(Collectors.toList());
    }
    public List<Car> getCarFixedList() {
        return carList.stream()
                .filter(car -> car.isStatus() == true)
                .sorted(Comparator.comparing(Car::getRepairDate).reversed())
                .collect(Collectors.toList());
    }

    public void addCar (Car car){
        carList.add(car);
    }
    public void saveCarToJson() throws IOException {
        Cars carsCopy = new Cars();
        for (Car car : carList){
            carsCopy.addCar(car);
        }
        fileService.writeCarToJson(carsCopy);
    }
    public List<Car> findCarByPlateList(String test){
        return carList.stream()
                .filter(car -> car.getLicensePlate().equals(test))
                .filter(car -> car.isStatus() == false)
                .collect(Collectors.toList());
    }
    public Car findCarByPlate(String plate){
        return carList.stream()
                .filter(car -> car.getLicensePlate().equals(plate))
                .findFirst()
                .orElseThrow(()->new CarNotFoundException("Not found"));
    }
    public void changeStatus (String plate){
        Car foundCar = findCarByPlate(plate);
        foundCar.setStatus(true);
        foundCar.setRepairDate(LocalDate.now().toString());
    }
}

