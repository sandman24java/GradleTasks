package task17.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import task17.dto.CarDto;
import task17.repository.CarDBRepositoryImpl;
import task17.service.CarService;
import task17.service.CarServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import task17.exception.CarNotFoundException;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name="cars",urlPatterns = {"/cars","/addcar","/deletecar","/updatecar"})
public class CarController extends HttpServlet {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private CarService carService;

    @Override
    public void init() throws ServletException {
        carService = new CarServiceImpl(new CarDBRepositoryImpl());


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/cars":
            try {
                String id = request.getParameter("id");
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
            break;
            default:
                response.getWriter().println("Invalid Request");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/addcar":
                response.setContentType("application/json");
                try (BufferedReader buffReader = request.getReader()) {
                    StringBuffer myString = new StringBuffer();
                    String line = null;
                    while ((line = buffReader.readLine()) != null) {
                        myString.append(line);
                    }
                    CarDto CarDto = OBJECT_MAPPER.readValue(myString.toString(), CarDto.class);
                    carService.addCar(CarDto);
                    response.setStatus(HttpServletResponse.SC_CREATED);
                }
                break;
            case "/deletecar":
                response.setContentType("application/json");
                try (BufferedReader buffReader = request.getReader()) {
                    StringBuffer myString = new StringBuffer();
                    String line = null;
                    while ((line = buffReader.readLine()) != null) {
                        myString.append(line);
                    }
                    CarDto CarDto = OBJECT_MAPPER.readValue(myString.toString(), CarDto.class);
                    carService.deleteCarById(CarDto.getId());
                    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                }
                break;
            case "/updatecar":
                response.setContentType("application/json");
                try (BufferedReader buffReader = request.getReader()) {
                    StringBuffer myString = new StringBuffer();
                    String line = null;
                    while ((line = buffReader.readLine()) != null) {
                        myString.append(line);
                    }
                    CarDto CarDto = OBJECT_MAPPER.readValue(myString.toString(), CarDto.class);
                    carService.updateCar(CarDto.getId(), CarDto);
                    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                }
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Unknown GET endpoint: " + path);
        }
    }
}











