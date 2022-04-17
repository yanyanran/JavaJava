package com.company;
import java.util.Random;

public class TextRandom {
    public static void main(String[] args){
            Random random = new Random(1000);
            System.out.println("From Random 1: ");
            for(int i = 0; i < 50;i++){
                System.out.print(random.nextInt(100) + " ");
            }
    }
}