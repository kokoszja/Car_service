package com.iso.carrepair.controller;

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
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/cars")
@Controller
public class CarControtoller {
    private final FileService fileService;
    private final CarService carService;
    private final Cars cars;

    public CarControtoller(FileService fileService, CarService carService, Cars cars) {
        this.fileService = fileService;
        this.carService = carService;
        this.cars = cars;
    }

    @GetMapping("/toBeFixed")
    public String carsToBeFixed (Model model) throws IOException {
        model.addAttribute("cars", carService.getCarToFixList());
//        carService.saveCarToJson(carService.getCarToFixList().get(0));
        return "cars/carsToBeFixed";
    }
    @GetMapping("/create")
    public String createCarTable (Model model){
        model.addAttribute("cars", new Car("Tablica", "Volvo", CarColor.BRĄZOWY, 2011, LocalDate.now().toString(), false));
        return "cars/create";
    }
    @PostMapping()
    public String addCar (@Valid @ModelAttribute Car car, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "cars/create";
        }
        carService.addCar(car);
        return "redirect:/carsToBeFixed";
    }
    @GetMapping("/save")
    public String saveCar() throws IOException {
        carService.saveCarToJson();
        return "redirect:/carsToBeFixed";
    }
}
