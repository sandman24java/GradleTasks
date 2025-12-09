package lesson5.service;

import lesson5.dto.CarDto;
import lesson5.model.CarEntity;

import java.util.List;

public interface CarService {
    List<CarDto> getCars();

    CarDto getCarById(int id);

    void addCar(CarDto carDto);

    void updateCar(int id, CarDto carDto);

    void deleteCarById(int id);
}
