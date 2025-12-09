package lesson5.service;

import lesson5.dto.CarDto;
import lesson5.exception.CarNotFoundException;
import lesson5.model.CarEntity;
import lesson5.repository.CarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;
    @InjectMocks
    private CarServiceImpl carService;
    @Test
    void getCarByIdSuccess(){
        Mockito.when(carRepository.getCarById(Mockito.anyInt()))
                .thenReturn(Optional.of(new CarEntity("Red",100)));
        CarDto actualCarDto = carService.getCarById(1);
        CarDto expectedCarDto = new CarDto("Red",100);
        Assertions.assertEquals(actualCarDto.getId(),expectedCarDto.getId());
        Assertions.assertEquals(actualCarDto.getColor(),expectedCarDto.getColor());
        Assertions.assertEquals(actualCarDto.getSpeed(),expectedCarDto.getSpeed());
    }
    @Test
    void getCarById_throw(){
        Mockito.when(carRepository.getCarById(Mockito.anyInt())).thenReturn(Optional.empty());
        Assertions.assertThrows(CarNotFoundException.class,()->carService.getCarById(1));

    }

}
