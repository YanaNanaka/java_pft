package ru.stqa.pft.sandbox;

public class Distance {
    public static void main(String[] args) {
        Point p1 = new Point(1, 5);
        Point p2 = new Point(4, 9);

        System.out.println("Расстояние между точками = " + (p1.distance(p2)));
    }
}