package com.iso.carrepair.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iso.carrepair.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Path;
import java.util.Objects;

@Service
public class FileService {
    private static final Path pathCarJsonFile = Path.of("CarRepair/src/main/java/com/iso/carrepair/database/car.json");
    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public void writeCarToJson(final CarRepository carRepository) throws IOException {
        FileWriter writer = new FileWriter(new File(pathCarJsonFile.toString()));
        gson.toJson(carRepository,writer);
        writer.close();
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

    public CarRepository readCarFromJsonFile(){
        CarRepository carRepository = readDataFromJsonFile(CarRepository.class, pathCarJsonFile);
        if (Objects.nonNull(carRepository)) {
            System.out.println("Plik właściwie zaimportowany");
            return carRepository;
        }else {
            return new CarRepository();
        }
    }
}
