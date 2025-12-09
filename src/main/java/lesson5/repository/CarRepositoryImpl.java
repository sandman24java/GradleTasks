package lesson5.repository;
import lesson5.model.CarEntity;
import java.util.*;

public class CarRepositoryImpl implements CarRepository {
    private static List<CarEntity> carEntities = new ArrayList<>();


    @Override
    public List<CarEntity> getCars() {
        return carEntities;
    }

    @Override
    public Optional<CarEntity> getCarById(int id) {
        return carEntities.stream()
                .filter(carEntity -> carEntity.getId()==id)
                .findFirst();

    }

    @Override
    public void saveCar(CarEntity carEntity) {
        carEntity.setId(carEntities.size()+1);
        carEntities.add(carEntity);

    }

    @Override
    public void updateCar(int id, CarEntity carEntityDto) {
        Optional<CarEntity> optional_car = getCarById(id);
        if (optional_car.isPresent()) {
            CarEntity carEntity = optional_car.get();
            carEntity.setColor(carEntityDto.getColor());
            carEntity.setSpeed(carEntityDto.getSpeed());
            carEntity.setDbCode(carEntityDto.getDbCode());
            carEntities.set(carEntities.indexOf(carEntity), carEntity);
        }
    }

    @Override
    public void deleteCarById(int id) {
        carEntities.removeIf(carEntity -> carEntity.getId()==id);
    }
}
