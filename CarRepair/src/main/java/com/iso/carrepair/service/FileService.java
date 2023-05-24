package com.iso.carrepair.service;

import com.google.gson.Gson;
import com.iso.carrepair.repository.Car;
import com.iso.carrepair.repository.Cars;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.Objects;

@Service
public class FileService {
    private static final Path pathCarJsonFile = Path.of("CarRepair/src/main/java/com/iso/carrepair/database/car.json");
    public static final Gson gson = new Gson();
    public void writeCarToJson(final Cars cars) throws IOException {
        FileWriter writer = new FileWriter(new File(pathCarJsonFile.toString()));
        gson.toJson(cars,writer);
        System.out.println("Zapisano w: " + pathCarJsonFile);
    }
    private <T> T readDataFromJsonFile(Class<T> dataType, Path path) {
        try (Reader reader = new FileReader(path.toFile())) {
            System.out.println("Zaczytuję plik: " + path);
            return gson.fromJson(reader, dataType);
        } catch (IOException e) {
            System.out.println("Plik nieznaleziony lub uszkodzony: " + e.getMessage());
            return null;
        }
    }

    public Cars readCarFromJsonFile(){
        Cars cars = readDataFromJsonFile(Cars.class, pathCarJsonFile);
        if (Objects.nonNull(cars)) {
            System.out.println("Plik właściwie zaimportowany");
            return cars;
        }else {
            return new Cars();
        }
    }
}
