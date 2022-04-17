package com.company;
import java.util.Date;

class Account{
    private int id;
    private double balance;
    private double annualInterestRate;
    private Date dateCreated;

    Account(){
        id = 0;
        balance = 0;
        annualInterestRate = 0;
    }
    Account(int uId, double uBalance){
        id = uId;
        balance = uBalance;
    }

    public void setId(int newId){
        id = newId;
    }
    public int getId(){
        return id;
    }

    public void setBalance(int newBalance){
        balance = newBalance;
    }
    public double getBalance() {
        return balance;
    }

    public void setInterestAnnualRate(double newAnnualRate){
        annualInterestRate = newAnnualRate;
    }
    public double getInterestAnnualRate(){
        return annualInterestRate;
    }

    public Date getDateCreated(){
        return dateCreated;
    }

    public double getMonthlyInterestRate(){
        return balance * annualInterestRate / 100 / 12;
    }
    public void withDraw(double withDrawBalance){
        balance -= withDrawBalance;
    }
    public void deposit(double depositBalance) {
        balance += depositBalance;
    }
}

public class TextAccount {
    public static void main(String[] args){
        Account account = new Account(1122,20000);
        account.setInterestAnnualRate(4.5);
        account.withDraw(2500);
        account.deposit(3000);
        System.out.println(account.getBalance() + "\n" + account.getMonthlyInterestRate() + "\n" + account.getDateCreated());
    }
}