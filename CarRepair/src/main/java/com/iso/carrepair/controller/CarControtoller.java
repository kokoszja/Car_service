package com.iso.carrepair.controller;

import com.iso.carrepair.repository.Car;
import com.iso.carrepair.repository.CarColor;
import com.iso.carrepair.repository.Cars;
import com.iso.carrepair.service.CarService;
import com.iso.carrepair.service.FileService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
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
    @GetMapping()
    public String carsMenu(){
        return "cars/menu";
    }

    @GetMapping("/toBeFixed")
    public String carsToBeFixed (Model model) throws IOException {
        model.addAttribute("cars", carService.getCarToFixList());
        return "cars/carsToBeFixed";
    }
    @GetMapping("/toBeFixed/addCar")
    public String createCarTable (Model model){
        model.addAttribute("car", new Car(null ,CarColor.BRĄZOWY.toString(), null, null, LocalDate.now().toString(), null, false));
        return "cars/addCar";
    }
    @PostMapping("/toBeFixed")
    public String addCar (@Valid @ModelAttribute Car car, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return "cars/addCar";
        }
        carService.addCar(car);
        carService.saveCarToJson();
        return "redirect:/cars/toBeFixed";
    }
    @GetMapping("/fixed")
    public String carFixed (Model model){
        model.addAttribute("cars", carService.getCarFixedList());
        return "cars/fixed";
    }
    @GetMapping("/find")
    public String findCar(@RequestParam (required = false) String keyword, Model model){
        List<Car> car = carService.findCarByPlateList(keyword);
        model.addAttribute("cars", car);
        model.addAttribute("keyword", keyword);
        return "cars/findCar";
    }
    @GetMapping("/status/{plate}")
    public String changeStatus (@PathVariable String plate) throws IOException {
        carService.changeStatus(plate);
        carService.saveCarToJson();
        return "redirect:/cars/toBeFixed";
    }
}
