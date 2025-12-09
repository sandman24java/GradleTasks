package lesson5.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lesson5.dto.CarDto;
import lesson5.repository.CarRepositoryImpl;
import lesson5.service.CarService;
import lesson5.service.CarServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lesson5.exception.CarNotFoundException;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name="cars",urlPatterns = "/cars")
public class CarController extends HttpServlet {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private CarService carService;

    @Override
    public void init() throws ServletException {
        carService = new CarServiceImpl(new CarRepositoryImpl());

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        var params = request.getParameterMap();
        try {
            if (id != null) {
                CarDto car = carService.getCarById(Integer.parseInt(id));
                response.getWriter().println(OBJECT_MAPPER.writeValueAsString(car));
            } else {
                var cars = carService.getCars();
                response.getWriter().println(OBJECT_MAPPER.writeValueAsString(cars));
            }
            response.setStatus(200);
            response.setContentType("application/json");
        } catch (Exception exception) {
            if (exception instanceof CarNotFoundException) {
                response.setStatus(404);
                response.getWriter().println("Car not found");
            } else {
                response.setStatus(500);
                response.getWriter().println("Internal Server Error");
            }

        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        try(BufferedReader  buffReader =  request.getReader()){
            StringBuffer myString = new StringBuffer();
            String line = null;
            while ((line = buffReader.readLine()) != null){
                myString.append(line);
            }
            CarDto CarDto = OBJECT_MAPPER.readValue(myString.toString(), CarDto.class);
            carService.addCar(CarDto);
            response.setStatus(HttpServletResponse.SC_CREATED);
        }



    }

}
