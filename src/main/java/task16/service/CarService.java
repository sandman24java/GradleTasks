package task16.service;

import task16.dto.CarDto;

import java.util.List;

public interface CarService {
    List<CarDto> getCars();

    CarDto getCarById(int id);

    void addCar(CarDto carDto);

    void updateCar(int id, CarDto carDto);

    void deleteCarById(int id);
}
