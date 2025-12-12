package task16.repository;

import task16.config.DBConfig;
import task16.exception.DBConnectionException;
import task16.model.CarEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDBRepositoryImpl implements CarRepository {
    @Override
    public List<CarEntity> getCars() {
        String query = "SELECT color,speed,name FROM database1.car";
        List<CarEntity> list = new ArrayList<>();
        try {
            try (Connection connection = DBConfig.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {

                    CarEntity carEntity = new CarEntity();
                }

            }

        }catch (SQLException e){
            e.printStackTrace();
        }




        try{
            Connection connection = DBConfig.getConnection();

        }catch(SQLException e){
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public Optional<CarEntity> getCarById(int id) {
        return Optional.empty();
    }

    @Override
    public void saveCar(CarEntity carEntity) {
        String query = "INSERT INTO database1.car (name,color,speed) VALUES (?, ?, ?)";
        try {
            Connection connection = DBConfig.getConnection();
            try (PreparedStatement st = connection.prepareStatement(query)) {
                st.setString(1, carEntity.getName());
                st.setString(2, carEntity.getColor());
                st.setInt(3, carEntity.getSpeed());
                st.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (DBConnectionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCar(int id, CarEntity carEntity) {

    }

    @Override
    public void deleteCarById(int id) {

    }
}
