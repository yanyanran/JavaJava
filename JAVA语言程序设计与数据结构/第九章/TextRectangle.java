package com.company;

import java.awt.*;
class Rectangle {
    double width;
    double height;

    Rectangle(){
        width = 1;
        height = 1;
    }

    Rectangle(double newWidth, double newHeight) {
        width = newWidth;
        height = newHeight;
    }

    public double getArea() {
        return width * height;
    }

    public double getPerimeter() {
        return width * 2 + height * 2;
    }
}

public class TextRectangle{
    public static void main(String[] args){
        Rectangle newHeight = new Rectangle(4,40);
        Rectangle newWidth = new Rectangle(3.5,35.9);
        System.out.println("rectangle 1's height is " + newHeight.height);
        System.out.println("rectangle 1's width is " + newHeight.getArea());
        System.out.println("rectangle 1's a is " + newHeight.getPerimeter());
    }
}
