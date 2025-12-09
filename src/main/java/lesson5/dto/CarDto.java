package lesson5.dto;

public class CarDto {
    private String color;
    private int speed;
    private int id;

    public CarDto(String color, int speed) {
        this.color = color;
        this.speed = speed;
    }
    public CarDto() {

    }

    public CarDto(CarBuilder builder) {
        this.color = builder.color;
        this.speed = builder.speed;
        this.id = builder.id;
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

    public static CarBuilder builder(){
        return new CarBuilder();
    }
    public static class CarBuilder {
        private String color;
        private int speed;
        private int id;
        public CarBuilder(){}

        public CarBuilder color(String color){
            this.color = color;
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





