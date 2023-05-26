package com.iso.carrepair.controller;

import com.google.gson.Gson;
import com.iso.carrepair.repository.Car;
import com.iso.carrepair.repository.CarColor;
import com.iso.carrepair.repository.Cars;
import com.iso.carrepair.service.CarService;
import com.iso.carrepair.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RequestMapping("/cars")
@Controller
public class CarControtoller {
    private final FileService fileService;
    private final CarService carService;
    private final Cars cars;
    List<Car> carList;

    public CarControtoller(FileService fileService, CarService carService, Cars cars) {
        this.fileService = fileService;
        this.carService = carService;
        this.cars = cars;
        this.carList = carService.allCars();
    }
    @GetMapping()
    public String carsMenu(){
        return "cars/menu";
    }

    @GetMapping("/toBeFixed")
    public String carsToBeFixed (Model model) throws IOException {
        model.addAttribute("cars", carService.allCars());
//        model.addAttribute("cars", carService.allCars());
        return "cars/carsToBeFixed";
    }
    @GetMapping("/toBeFixed/addCar")
    public String createCarTable (Model model){
        model.addAttribute("cars", new Car("Tablica", "Volvo", CarColor.BRĄZOWY, 2011, LocalDate.now().toString(), false));
        return "cars/addCar";
    }
    @PostMapping("/toBeFixed")
    public String addCar (@Valid @ModelAttribute Car car, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()){
            return "cars/carsToBeFixed";
        }
        carService.saveCarToJson();
        return "redirect:/cars/carsToBeFixed";
    }
    @GetMapping("/save")
    public String saveCar() throws IOException {
        carList.add(new Car("Tablica", "Volvo", CarColor.BRĄZOWY, 2011, LocalDate.now().toString(), false));
        carList.add(new Car("Tablica", "Volvo", CarColor.BRĄZOWY, 2011, LocalDate.now().toString(), false));
        carList.add(new Car("Tablica", "Volvo", CarColor.BRĄZOWY, 2011, LocalDate.now().toString(), false));
        carService.saveCarToJson();
        return "redirect:/cars/toBeFixed";
    }
}
