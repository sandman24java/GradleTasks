package dto;

import jakarta.servlet.http.HttpServlet;

public class NumberAdder  {
    private int a;
    private int b;

    public int getA() {
        return a;
    }
    public int getB() {
        return b;
    }
    public int add(int a, int b) {
        return a + b;
    }
}