package task16_17.service;

import task16_17.dto.CarDto;
import task16_17.exception.CarNotFoundException;
import task16_17.model.CarEntity;
import task16_17.repository.CarRepository;

import java.util.List;

// Карсервис делает обратную логику он преобразовывает CarEntity в CarDto
public class CarServiceImpl implements CarService {
    // CarRepository — это объект доступа к данным
    private final CarRepository carRepository;
//Когда создаётся CarServiceImpl, ему внедряют (передают) репозиторий, с которым он должен работать.
    // Dependency injection
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<CarDto> getCars() {
        return carRepository.getCars().stream()
                .map(carEntity -> CarDto
                        .builder()
                        .name(carEntity.getName())
                        .color(carEntity.getColor())
                        .speed(carEntity.getSpeed())
                        .build())
                .toList();
    }

    @Override
    public CarDto getCarById(int id) {
        CarEntity car = carRepository.getCarById(id).orElseThrow(()-> new CarNotFoundException("Car not found"));
        return CarDto.builder().color(car.getColor()).speed(car.getSpeed()).build();
    }

    @Override
    public void addCar(CarDto carDto) {
        carRepository.saveCar(new CarEntity(carDto.getColor(),carDto.getSpeed(),carDto.getName()));
    }

    @Override
    public void updateCar(int id, CarDto carDto) {
        carRepository.updateCar(id, new CarEntity(carDto.getColor(),carDto.getSpeed(),carDto.getName()));

    }

    @Override
    public void deleteCarById(int id) {
        carRepository.deleteCarById(id);
    }
}
