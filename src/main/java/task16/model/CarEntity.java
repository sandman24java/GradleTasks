package task16.model;

import java.util.Objects;

public class CarEntity {

    private String color;
    private int speed;
    private int id;
    private String dbCode;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CarEntity carEntity = (CarEntity) o;
        return speed == carEntity.speed && id == carEntity.id && Objects.equals(color, carEntity.color) && Objects.equals(dbCode, carEntity.dbCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, speed, id, dbCode);
    }

    public CarEntity(String color, int speed) {
        this.color = color;
        this.speed = speed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDbCode() {
        return dbCode;
    }

    public void setDbCode(String dbCode) {
        this.dbCode = dbCode;
    }

    @Override
    public String toString() {
        return "CarEntity{" +
                "color='" + color + '\'' +
                ", speed=" + speed +
                ", id=" + id +
                ", dbCode='" + dbCode + '\'' +
                '}';
    }
}


