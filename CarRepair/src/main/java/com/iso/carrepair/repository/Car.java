package com.iso.carrepair.repository;

import org.springframework.lang.NonNull;

import java.time.LocalDate;

public class Car {
    private int id;
    @NonNull
    private String licensePlate;
    @NonNull
    private String makeOfCar;
    private CarColor carColor;

    private int yearOfproduction;
    private LocalDate acceptanceForService;
    private boolean status;



}
