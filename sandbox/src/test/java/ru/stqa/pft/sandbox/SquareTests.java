package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SquareTests {
    @Test
    public void testArea() {
        Square s = new Square(5);
        Assert.assertEquals(s.area(), 20.0);
    }
    @Test
    public void testDistance() {
        Point p1 = new Point(1, 5);
        Point p2 = new Point(4, 9);
        Assert.assertEquals((p1.distance(p2)), 5.0);
    }
}
