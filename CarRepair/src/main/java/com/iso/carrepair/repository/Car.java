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
    private String modelOfCar;

    private String carColor;
    @Min(value = 1950)
    @Max(value = 2023)
    private Integer yearOfproduction;
    private String acceptanceForService;
    private String repairDate;
    private boolean status;

    public Car( String licensePlate,String carColor, String modelOfCar, Integer yearOfproduction, String acceptanceForService,String repairDate, boolean status) {
        this.licensePlate = licensePlate;
        this.modelOfCar = modelOfCar;
        this.carColor = carColor;
        this.yearOfproduction = yearOfproduction;
        this.acceptanceForService = acceptanceForService;
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
        return modelOfCar;
    }

    public void setMakeOfCar(String makeOfCar) {
        this.modelOfCar = makeOfCar;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public Integer getYearOfproduction() {
        return yearOfproduction;
    }

    public void setYearOfproduction(Integer yearOfproduction) {
        this.yearOfproduction = yearOfproduction;
    }

    public String getAcceptanceForService() {
        return acceptanceForService;
    }

    public void setAcceptanceForService(String acceptanceForService) {
        this.acceptanceForService = acceptanceForService;
    }

    public String getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(String repairDate) {
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
        return id == car.id && yearOfproduction == car.yearOfproduction && status == car.status && Objects.equals(licensePlate, car.licensePlate) && Objects.equals(modelOfCar, car.modelOfCar) && carColor == car.carColor && Objects.equals(acceptanceForService, car.acceptanceForService) && Objects.equals(repairDate, car.repairDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, licensePlate, modelOfCar, carColor, yearOfproduction, acceptanceForService, repairDate, status);
    }

    @Override
    public String toString() {
        return "Car{" +
                "licensePlate='" + licensePlate + '\'' +
                ", makeOfCar='" + modelOfCar + '\'' +
                ", carColor=" + carColor +
                '}';
    }
}
