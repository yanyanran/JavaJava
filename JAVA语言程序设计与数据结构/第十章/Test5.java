import java.util.Scanner;

public class Test5 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int number = input.nextInt();
        StackOfIntegers stack = new StackOfIntegers();

        for (int i = 0;i <= number ; ){
            if(number % i == 0 && isPrime(i)){
                stack.push(i);
                number /= i;
                i = 2;
            }else i++;
        }
        while(!stack.empty()){
            System.out.print(stack.pop() + " ");
        }
    }
    public static boolean isPrime(int number){
        for(int j = 2;j <= number / 2; j++){
            if(number % j == 0){
                return false;
            }
        }
        return true;
    }
}