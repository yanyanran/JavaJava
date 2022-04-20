import java.util.Date;
import java.util.Scanner;

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

public class Test7 {
    public static void main(String[] args){
        Account[] account = new Account[10];
        for(int i = 0; i < 10; i++){
            account[i] = new Account(i,100);
        }

        System.out.println("Enter a id: ");
        Scanner input = new Scanner(System.in);
        int id = input.nextInt();
        while(id < 0 || id > 9){
            System.out.print("error, enter a true id again: ");
            id = input.nextInt();
        }
        mainMenu();
        int choice = input.nextInt();
        boolean judge = choice == 1 || choice == 2 || choice ==3;
        while(judge){
            switch(choice){
                case 1:
                    System.out.println("The balance is " + account[id].getBalance());
                    break;
                case 2:
                    System.out.println("Enter an amount to withdraw: ");
                    double withdraw = input.nextDouble();
                    account[id].withDraw(withdraw);
                    break;
                case 3:
                    System.out.println("Enter an amount to deposit: ");
                    double deposit = input.nextDouble();
                    account[id].deposit(deposit);
                    break;
            }
            mainMenu();
            choice = input.nextInt();
            judge = choice == 1 || choice ==2 || choice == 3;
        }
        Test7.main(args);
        }

    public static void mainMenu() {
        System.out.println("Main menu");
        System.out.println("1: check balance ");
        System.out.println("2: withdraw ");
        System.out.println("3: deposit ");
        System.out.println("4: exit ");
        System.out.print("Enter a choice: ");
    }
}