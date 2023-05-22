package com.iso.carrepair.repository;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.Objects;

@Service
public class FileService {
    private static final Path pathCarJsonFile = Path.of("CarRepair/src/main/java/com/iso/carrepair/database/car.json");
    public static final Gson gson = new Gson();
    public void writeToJsonFile ( Car car){
        try {
            gson.toJson(car,new FileWriter("CarRepair/src/main/java/com/iso/carrepair/database/car.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
