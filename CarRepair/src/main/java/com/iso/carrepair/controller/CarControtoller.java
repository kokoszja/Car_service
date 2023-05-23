package com.iso.carrepair.controller;

import com.iso.carrepair.repository.Car;
import com.iso.carrepair.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/cars")
@Controller
public class CarControtoller {
    private CarService carService;
    List<Car> carList = new ArrayList<>();
    public CarControtoller(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/toBeFixed")
    public String carsToBeFixed (Model model) throws IOException {
        model.addAttribute("cars", carService.getCarToFixList());
//        carService.saveCarToJson(carService.getCarToFixList().get(0));
        return "cars/carsToBeFixed";
    }
}
