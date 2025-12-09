package lesson5.repository;

import lesson5.model.CarEntity;

import java.util.List;
import java.util.Optional;

public interface CarRepository {
    List<CarEntity> getCars();

    Optional<CarEntity> getCarById(int id);

    void saveCar(CarEntity carEntity);

    void updateCar(int id, CarEntity carEntity);

    void deleteCarById(int id);

}
