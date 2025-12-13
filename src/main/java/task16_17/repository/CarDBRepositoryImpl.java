package task16_17.repository;

import task16_17.config.DBConfig;
import task16_17.exception.DBConnectionException;
import task16_17.model.CarEntity;

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
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String color = resultSet.getString("color");
                    Integer speed = resultSet.getInt("speed");
                    String name = resultSet.getString("name");
                    CarEntity carEntity = new CarEntity(color, speed, name);
                    list.add(carEntity);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public Optional<CarEntity> getCarById(int id) {
        String query = "SELECT * from database1.car where id = ?";
        CarEntity carEntity = null;
            try (Connection connection = DBConfig.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String color = resultSet.getString("color");
                    Integer speed = resultSet.getInt("speed");
                    String name = resultSet.getString("name");
                    carEntity = new CarEntity(color, speed, name);
                }
                return Optional.ofNullable(carEntity);
            }catch (SQLException e){
                e.printStackTrace();
            }
        return Optional.ofNullable(carEntity);
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
        String query = "UPDATE database1.car SET color = ?, speed = ?,name=? WHERE id = ?";
        try(Connection connection = DBConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, carEntity.getColor());
            preparedStatement.setInt(2, carEntity.getSpeed());
            preparedStatement.setString(3,carEntity.getName());
            preparedStatement.setInt(4,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void deleteCarById(int id) {
        String query = "DELETE FROM database1.car where id = ?";
        try(Connection connection = DBConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
