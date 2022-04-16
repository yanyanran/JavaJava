package com.company;
class Stock{
    String symbol;
    String name;
    double previousClosingPrice;
    double currentPrice;

    Stock(String newSymbol, String newName) {
        symbol = newSymbol;
        name = newName;
    }

    public void setCurrentPrice(double newCurrentPrice){
        currentPrice = newCurrentPrice;
    }
    public void setPreviousClosingPrice(double newPreviousClosingPrice) {
        previousClosingPrice = newPreviousClosingPrice;
    }

    public double getChangePercent() {
        return (currentPrice - previousClosingPrice) / previousClosingPrice;
    }
}

public class TextStock {
    public static void main(String[] args){
        Stock stock1 = new Stock("ORCL", "Oracle Corporation");
        stock1.setPreviousClosingPrice(34.5);
        stock1.setCurrentPrice(34.35);
        System.out.printf("The change percent is %5.2f%%\n", stock1.getChangePercent() * 100);
    }
}