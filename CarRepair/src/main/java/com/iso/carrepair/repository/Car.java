package com.iso.carrepair.repository;

import org.springframework.lang.NonNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Objects;

public class Car {
    private long id;
    @NotBlank
    private String licensePlate;
    @NotBlank
    private String makeOfCar;

    private CarColor carColor;
    @Min(value = 1950)
    @Max(value = 2023)
    private int yearOfproduction;
    private LocalDate acceptanceForService;
    private LocalDate repairDate;
    private boolean status;

    public Car(int id, String licensePlate, String makeOfCar, CarColor carColor, int yearOfproduction, LocalDate acceptanceForService, LocalDate repairDate, boolean status) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.makeOfCar = makeOfCar;
        this.carColor = carColor;
        this.yearOfproduction = yearOfproduction;
        this.acceptanceForService = LocalDate.now();
        this.repairDate = repairDate;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getMakeOfCar() {
        return makeOfCar;
    }

    public void setMakeOfCar(String makeOfCar) {
        this.makeOfCar = makeOfCar;
    }

    public CarColor getCarColor() {
        return carColor;
    }

    public void setCarColor(CarColor carColor) {
        this.carColor = carColor;
    }

    public int getYearOfproduction() {
        return yearOfproduction;
    }

    public void setYearOfproduction(int yearOfproduction) {
        this.yearOfproduction = yearOfproduction;
    }

    public LocalDate getAcceptanceForService() {
        return acceptanceForService;
    }

    public void setAcceptanceForService(LocalDate acceptanceForService) {
        this.acceptanceForService = acceptanceForService;
    }

    public LocalDate getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(LocalDate repairDate) {
        this.repairDate = repairDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id && yearOfproduction == car.yearOfproduction && status == car.status && Objects.equals(licensePlate, car.licensePlate) && Objects.equals(makeOfCar, car.makeOfCar) && carColor == car.carColor && Objects.equals(acceptanceForService, car.acceptanceForService) && Objects.equals(repairDate, car.repairDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, licensePlate, makeOfCar, carColor, yearOfproduction, acceptanceForService, repairDate, status);
    }

    @Override
    public String toString() {
        return "Car{" +
                "licensePlate='" + licensePlate + '\'' +
                ", makeOfCar='" + makeOfCar + '\'' +
                ", carColor=" + carColor +
                '}';
    }
}
