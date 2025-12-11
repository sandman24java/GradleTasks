package task16.repository;


import task16.model.CarEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;


public class CarRepositoryImplTest {
    private static List<CarEntity> carEntities;
    @BeforeEach
    public  void setup() {
        carEntities = List.of(
                new CarEntity("Red",100)
                ,new CarEntity("Blue",150)
                ,new CarEntity("Green",200));
    }

    @Test
    public void saveCarTest(){
        CarRepository repository1 = new CarRepositoryImpl();

        var toBeCompared = new CarEntity("Red",100);
        toBeCompared.setId(1);
        var carExpected = List.of(toBeCompared);
        repository1.saveCar(carEntities.get(0));
        var carActual = repository1.getCars();
        Assertions.assertEquals(carExpected,carActual);
    }

    @Test
    public void getCarsTest(){
        CarRepository repository2 = new CarRepositoryImpl();
            for(CarEntity carEntity : carEntities){
                repository2.saveCar(carEntity);
            }
        List<CarEntity> actualList = repository2.getCars();
        List<CarEntity> expectedList = carEntities;

            Assertions.assertEquals(expectedList,actualList);
    }
    @Test
    public void getCarByIdTestNotNull(){
        CarRepository repository3 = new CarRepositoryImpl();
        repository3.saveCar(carEntities.get(0));
        var actual = repository3.getCarById(carEntities.get(0).getId());
        CarEntity expectedObj = new CarEntity("Red",100);
        expectedObj.setId(1);
        var expected = Optional.of(expectedObj);
        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void getCarByIdTestNull(){
        CarRepository repository4 = new CarRepositoryImpl();
        var actual = repository4.getCarById(0);
        Assertions.assertTrue(actual.isEmpty());
    }
    @Test
    public void updateCarTestIdExists(){
        CarRepository repository5 = new CarRepositoryImpl();
        CarEntity carEntityOld = carEntities.get(0);
        repository5.saveCar(carEntityOld);
        CarEntity carEntityNew = new CarEntity("Blue",150);
        repository5.updateCar(1,carEntityNew);
        var carEntityActual = repository5.getCars().getFirst();

        CarEntity toCheck = new CarEntity("Blue",150);
        toCheck.setId(1);
        CarEntity carEntityExpected = toCheck;
        Assertions.assertEquals(carEntityExpected,carEntityActual);


    }
    @Test
    public void updateCarTestIdNotExists(){
        CarRepository repository6 = new CarRepositoryImpl();
        CarEntity carEntityOld = carEntities.get(0);
        repository6.saveCar(carEntityOld);
        CarEntity carEntityNew = new CarEntity("Blue",150);
        repository6.updateCar(999,carEntityNew);
        var carEntityActual = repository6.getCars().getFirst();
        CarEntity toCheck = new CarEntity("Red",100);
        toCheck.setId(1);
        CarEntity carEntityExpected = toCheck;
        Assertions.assertEquals(carEntityExpected,carEntityActual);



    }
    @Test
    public void deleteCarTest(){
        CarRepository repository7 = new CarRepositoryImpl();
        repository7.saveCar(carEntities.get(0));
        repository7.deleteCarById(1);
        Assertions.assertTrue(repository7.getCars().isEmpty());
    }



}
