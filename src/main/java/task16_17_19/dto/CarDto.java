package task16_17_19.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CarDto {
    private String color;
    private int speed;
    @JsonIgnore
    private int id;
    private String name;

    public CarDto(String color, int speed,String name) {
        this.color = color;
        this.speed = speed;
        this.name = name;
    }
    public CarDto() {

    }

    public CarDto(CarBuilder builder) {
        this.color = builder.color;
        this.speed = builder.speed;
        this.id = builder.id;
        this.name = builder.name;
    }
    public String getColor() {
        return color;
    }
    public int getSpeed() {
        return speed;
    }
    public int getId() {
        return id;
    }
    public String getName() {return  name;}

    public static CarBuilder builder(){
        return new CarBuilder();
    }
    public static class CarBuilder {
        private String color;
        private int speed;
        private int id;
        private String name;
        public CarBuilder(){}

        public CarBuilder color(String color){
            this.color = color;
            return this;
        }
        public CarBuilder name(String name){
            this.name = name;
            return this;
        }
        public CarBuilder speed(int speed){
            this.speed = speed;
            return this;
        }
        public CarBuilder id(int id){
            this.id = id;
            return this;
        }
        public CarDto build() {
            return new CarDto(this);
        }


    }





}





