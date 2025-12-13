package task17.model;

import java.util.Objects;

public class CarEntity {
    private String name;
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

    public CarEntity(String color, int speed, String name) {
        this.color = color;
        this.speed = speed;
        this.name = name;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Color is " + color + ", Speed is " + speed + ", Name is " + name;
    }
}


