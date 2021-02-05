package ru.stqa.pft.sandbox;

public class Example {
    public static void main(String args[]) {
        program("Java");
        program("JavaScript");
        program("C++");
        double m = 5;

        System.out.println("Площадь квадрата со стороной " + m + " = " + area(m));

        double a = 2;
        double b = 8;
        System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b + " = " + area(a, b));

    }
    public static void program(String somebody) {
        System.out.println("Простая программа на " + somebody);
    }
    public static double area(double l) {
        return l * l;
    }
    public static double area(double a, double b) {
        return a * b;
    }
}

