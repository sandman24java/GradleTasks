package task17.repository;


import org.junit.jupiter.api.AfterEach;
import task17.model.CarEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class CarRepositoryImplTest {

    private CarRepository carRepository;
    private List<CarEntity> carEntities;

    @BeforeEach
    public void setup() {
        carRepository = new CarRepositoryImpl();
        carEntities = new ArrayList<>();
        carEntities.add(new CarEntity("Red", 150,"Ferrari"));
        carEntities.add(new CarEntity("Blue", 100,"Mercedes"));

    }

    @AfterEach
    public void tearDown() {
        List<CarEntity> copy = new ArrayList<>(carRepository.getCars());
        for (CarEntity car : copy) {
            carRepository.deleteCarById(car.getId());
        }
    }

    @Test
    public void saveCarTest() {
        System.out.println(carEntities);
        System.out.println(carRepository.getCars());
        var toBeCompared = new CarEntity("Red", 150,"Ferrari");
        toBeCompared.setId(1);
        var carExpected = List.of(toBeCompared);
        carRepository.saveCar(new CarEntity("Red", 150,"Ferrari"));
        var carActual = carRepository.getCars();
        Assertions.assertEquals(carExpected, carActual);
    }

    @Test
    public void getCarsTest() {
        System.out.println(carEntities);
        System.out.println(carRepository.getCars());
        for (CarEntity carEntity : carEntities) {
            carRepository.saveCar(carEntity);
        }
        List<CarEntity> actualList = carRepository.getCars();
        List<CarEntity> expectedList = carEntities;
        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    public void getCarByIdTestNotNull() {
        CarEntity carEntity = carEntities.get(0);
        carEntity.setId(1);
        carRepository.saveCar(carEntity);

        var actual = carRepository.getCarById(carEntities.get(0).getId());
        System.out.println(actual);
        CarEntity expectedObj = new CarEntity("Red", 150,"Ferrari");
        expectedObj.setId(1);
        var expected = Optional.of(expectedObj);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getCarByIdTestNull() {
        var actual = carRepository.getCarById(0);
        Assertions.assertTrue(actual.isEmpty());
    }

    @Test
    public void updateCarTestIdExists() {
        CarEntity carEntityOld = carEntities.get(0);
        carRepository.saveCar(carEntityOld);
        CarEntity carEntityNew = new CarEntity("Blue", 150,"Mercedes");
        carRepository.updateCar(1, carEntityNew);
        var carEntityActual = carRepository.getCars().getFirst();

        CarEntity toCheck = new CarEntity("Blue", 150,"Mercedes");
        toCheck.setId(1);
        CarEntity carEntityExpected = toCheck;
        Assertions.assertEquals(carEntityExpected, carEntityActual);
    }

    @Test
    public void updateCarTestIdNotExists() {
        CarEntity carEntityOld = carEntities.get(0);
        carRepository.saveCar(carEntityOld);
        CarEntity carEntityNew = new CarEntity("Blue", 150,"Mercedes");
        carRepository.updateCar(999, carEntityNew);
        var carEntityActual = carRepository.getCars().getFirst();
        CarEntity carEntityExpected = new CarEntity("Red", 150,"Ferrari");
        carEntityExpected.setId(1);
        Assertions.assertEquals(carEntityExpected, carEntityActual);
    }

    @Test
    public void deleteCarTest(){

        carRepository.saveCar(carEntities.get(0));
        carRepository.deleteCarById(1);
        Assertions.assertTrue(carRepository.getCars().isEmpty());
    }

}
