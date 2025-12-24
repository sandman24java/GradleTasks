package task16_17_19.service;

import task16_17_19.dto.CarDto;
import task16_17_19.exception.CarNotFoundException;
import task16_17_19.model.CarEntity;
import task16_17_19.repository.CarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;
    @InjectMocks
    private CarServiceImpl carService;
    @Test
    void getCarByIdSuccess(){
        // Слушай, моковый carRepository, если сервис позовёт тебя методом getCarById(...)
        //с любой цифрой,верни ему Optional(CarEntity("Red",100))
        Mockito.when(carRepository.getCarById(Mockito.anyInt()))
                .thenReturn(Optional.of(new CarEntity("Red",100,"Ferrari")));
        CarDto actualCarDto = carService.getCarById(1);
        // Я проверяю преобразование CarEntity-> CarDto
        CarDto expectedCarDto = new CarDto("Red",100,"Ferrari");
        Assertions.assertEquals(actualCarDto.getId(),expectedCarDto.getId());
        Assertions.assertEquals(actualCarDto.getColor(),expectedCarDto.getColor());
        Assertions.assertEquals(actualCarDto.getSpeed(),expectedCarDto.getSpeed());
    }
    @Test
    void getCarById_throw(){
        Mockito.when(carRepository.getCarById(Mockito.anyInt())).thenReturn(Optional.empty());
        Assertions.assertThrows(CarNotFoundException.class,()->carService.getCarById(1));
    }
    @Test
    void getCarsTest(){
        Mockito.when(carRepository.getCars()).thenReturn(List.of(new CarEntity("Red",100,"Ferrari")));
        List<CarDto> actualList = carService.getCars();
        List<CarDto> expectedList = List.of(new CarDto("Red",100,"Ferrari"));
        Assertions.assertEquals(actualList.get(0).getSpeed(),expectedList.get(0).getSpeed());
        Assertions.assertEquals(actualList.get(0).getColor(),expectedList.get(0).getColor());
    }
    // Если метод войд то: есть метод .verify он спрашивает у метода сервиса:
    // Ты вызывал метод моего мока? И с теми же полями или нет?
    @Test
    void addCarTest(){
        CarDto carDto = new CarDto("Red",100,"Ferrari");
        carService.addCar(carDto);
        Mockito.verify(carRepository).saveCar(new CarEntity("Red",100,"Ferrari"));
    }
    @Test
    void updateCarTestIdExists(){
        CarDto carDto = new CarDto("Red",100,"Ferrari");
        carService.updateCar(1,carDto);
        Mockito.verify(carRepository).updateCar(1,new CarEntity("Red",100,"Ferrari"));
    }
    @Test
    void deleteCarByIdTest(){
        carService.deleteCarById(1);
        Mockito.verify(carRepository).deleteCarById(1);
    }



}
