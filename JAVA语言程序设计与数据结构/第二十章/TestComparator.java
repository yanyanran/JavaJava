import java.awt.*;
import java.util.Comparator;
import java.util.Scanner;

abstract class GeometricObject {
    private String color = "white";
    private boolean filled = false;
    public GeometricObject() {
    }
    public GeometricObject(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public boolean getFilled() {
        return filled;
    }
    public void setFilled(boolean filled) {
        this.filled = filled;
    }
    public abstract double getArea();
    public abstract double getPerimeter();
}

class Circle extends GeometricObject {
    private int radius;
    public Circle(){
        radius = 2;
        System.out.println("this is a constructor");
    }
    public Circle(int r){
        if(r <= 0)
            radius = 2;
        else
            radius = r;
        System.out.println("this is a constructor with para");
    }
    public void setter(int r){
        if(r <= 0)
            radius = 2;
        else
            radius = r;
    }
    public int getter(){
        return radius;
    }
    public double getArea(){
        double A = radius*radius*Math.PI;
        return A;
    }
    public String toString(){
        return "Circle [radius=" + radius + "]";
    }
    public double getPerimeter() {
        return 2;
    }
}

class Rectangle extends GeometricObject{
    private double width = 5.0;
    private double height = 4.0;
    public Rectangle() {
        this.width = 5.0;
        this.height = 4.0;
    }
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public double getArea() {
        return width * height;
    }
    public double getPerimeter() {
        return 2 * (width + height);
    }
}

class GeometricObjectComparator implements Comparator<GeometricObject>{
    @Override
    public int compare(GeometricObject o1, GeometricObject o2){
        double area1 = o1.getArea();
        double area2 = o2.getArea();
        if(area1 < area2)
            return -1;
        else if(area1 == area2)
            return 0;
        else
            return 1;
    }
}

// main
public class TestComparator {
    public static void main(String[] args) {
        GeometricObject g1 = new Rectangle(5,5);
        GeometricObject g2 = new Circle(5);

        GeometricObject g = max(g1, g2, new GeometricObjectComparator());
        System.out.println("The area of thr larger object is " + g.getArea());
    }

    public static GeometricObject max(GeometricObject g1, GeometricObject g2, Comparator<GeometricObject> c) {
        if(c.compare(g1,g2) > 0)
            return g1;
        else
            return g2;
    }
}