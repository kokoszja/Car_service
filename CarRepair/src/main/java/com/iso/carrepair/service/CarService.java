package com.iso.carrepair.service;

import com.iso.carrepair.exception.CarNotFoundException;
import com.iso.carrepair.repository.Car;
import com.iso.carrepair.repository.CarColor;
import com.iso.carrepair.repository.FileService;
import org.json.JSONArray;
import org.json.JSONException;
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
    private List<Car> carList;
    public List<Car> carsForTest(){
        carList.add(new Car("DWR 1565C", "Volvo", CarColor.BRĄZOWY, 2010,LocalDate.now(),LocalDate.now().plusWeeks(2l),false));
        return carList;
    }

    public CarService( FileService fileService) {
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
    private static final String pathUsersFile = "CarRepair/src/main/java/com/iso/carrepair/database/car.json";
    public void saveCarToJson(List<Car> cars) throws IOException {
        try {
            JSONArray jsonArray = new JSONArray(carList.toArray());
            FileWriter writer = new FileWriter(new File(pathUsersFile));
            writer.write(jsonArray.toString());
            writer.close();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
}
