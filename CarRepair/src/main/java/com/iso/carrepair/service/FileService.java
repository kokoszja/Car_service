package com.iso.carrepair.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iso.carrepair.repository.Cars;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Objects;

@Service
public class FileService {
    private static final Path pathCarJsonFile = Path.of("CarRepair/src/main/java/com/iso/carrepair/database/car.json");
    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public void writeCarToJsonToBeFixed(final Cars cars) throws IOException {
        FileWriter writer = new FileWriter(new File(pathCarJsonFile.toString()));
        gson.toJson(cars,writer);
        writer.close();
        System.out.println("Zapisano w: " + pathCarJsonFile);
    }
    private <T> T readDataFromJsonFileToBeFixed(Class<T> dataType, Path path) {
        try (Reader reader = new FileReader(path.toFile())) {
            System.out.println("Zaczytuję plik: " + path);
            return gson.fromJson(reader, dataType);
        } catch (IOException e) {
            System.out.println("Plik nieznaleziony lub uszkodzony: " + e.getMessage());
            return null;
        }
    }

    public Cars readCarFromJsonFileToBeFixed(){
        Cars cars = readDataFromJsonFileToBeFixed(Cars.class, pathCarJsonFile);
        if (Objects.nonNull(cars)) {
            System.out.println("Plik właściwie zaimportowany");
            return cars;
        }else {
            return new Cars();
        }
    }
    public File fileFixed() throws IOException {
        File actualFile = new File("CarRepair/src/main/java/com/iso/carrepair/database/fixed/" + LocalDate.now().toString() + ".json");
            if(!actualFile.exists()){
                actualFile.createNewFile();
            }
            return actualFile;
    }
    public void writeCarToJsonFixed(final Cars cars) throws IOException {
        FileWriter writer = new FileWriter(fileFixed());
        gson.toJson(cars,writer);
        writer.close();
        System.out.println("Zapisano w: CarRepair/src/main/java/com/iso/carrepair/database/fixed/" + LocalDate.now().toString() + ".json");
    }
}
