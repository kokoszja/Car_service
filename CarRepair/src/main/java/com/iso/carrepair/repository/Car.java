package com.iso.carrepair.repository;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class Car {
    @Size(min = 3)
    private String licensePlate;
    @Size(min = 3)
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

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getModelOfCar() {
        return modelOfCar;
    }

    public void setMakeOfCar(String modelOfCar) {
        this.modelOfCar = modelOfCar;
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
        return yearOfproduction == car.yearOfproduction && status == car.status && Objects.equals(licensePlate, car.licensePlate) && Objects.equals(modelOfCar, car.modelOfCar) && carColor == car.carColor && Objects.equals(acceptanceForService, car.acceptanceForService) && Objects.equals(repairDate, car.repairDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(licensePlate, modelOfCar, carColor, yearOfproduction, acceptanceForService, repairDate, status);
    }

    @Override
    public String toString() {
        return "Car{" +
                "licensePlate='" + licensePlate + '\'' +
                ", modelOfCar='" + modelOfCar + '\'' +
                ", carColor=" + carColor +
                '}';
    }
}
