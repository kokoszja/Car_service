package com.iso.carrepair.controller;

import com.iso.carrepair.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarControtoller {
    private CarService carService;

    @GetMapping("/")
    public String allCars (Model model){
        model.addAttribute("cars", carService.getCarFixedList());
        return "cars/cars";
    }
}
