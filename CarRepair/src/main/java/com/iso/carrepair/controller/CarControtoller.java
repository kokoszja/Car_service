package com.iso.carrepair.controller;

import com.iso.carrepair.repository.Car;
import com.iso.carrepair.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CarControtoller {
    private CarService carService;
    public CarControtoller(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/")
    public String allCars (Model model) throws IOException {
        model.addAttribute("cars", carService.carsForTest());
        return "cars/cars";
    }
}
