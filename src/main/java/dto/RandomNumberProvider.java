package dto;

public class RandomNumberProvider {
    public int generateNumber(){
        return (int) (Math.random() * 100);
    }

}
