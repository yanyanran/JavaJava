import java.util.Scanner;

public class Test6 {
    public static void main(String[] args){
        Scanner input  = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int number = input.nextInt();
        StackOfIntegers stack = new StackOfIntegers();

        for(int i = 0;i < number; i++){
            if(isPrime(i)){
                stack.push(i);
            }
        }
        while(!stack.empty()){
            System.out.println(stack.pop() + " ");
        }
    }
    public static boolean isPrime(int num){
        if(num == 0 || num == 1){
            return false;
        }
        for(int i = 2; i <=num / 2 ; i++){
            if(num % i == 0 ){
                return false;
            }
        }
        return true;
    }
}