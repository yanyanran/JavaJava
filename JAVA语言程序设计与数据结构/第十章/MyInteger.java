public class MyInteger {
    private int value;

    public MyInteger(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    public boolean isEven(){
        return value % 2 ==0;
    }
    public boolean isOdd(){
        return !isEven();
    }
    public boolean isPrime(){
        if(value == 0 || value == 1){
            return false;
        }
        for(int i = 2;i < this.value;i++){
            if(value % i == 0){
                return false;
            }
        }
        return true;
    }

    public static boolean isEven(int value){
        return new MyInteger(value).isEven();
    }
    public static boolean isOdd(int value){
        return new MyInteger(value).isOdd();
    }
    public static boolean isPrime(int value){
        return new MyInteger(value).isPrime();
    }

    public static boolean isEven(MyInteger myInteger){
        return myInteger.isEven();
    }
    public static boolean isOdd(MyInteger myInteger){
        return myInteger.isOdd();
    }
    public static boolean isPrime(MyInteger myInteger){
        return myInteger.isPrime();
    }

    public boolean equals(int value){
        return this.value == value;
    }
    public boolean equals(MyInteger myInteger){
        return this.equals(myInteger.value);
    }

    public static int parseInt(char[] chars){
        String string = new String(chars);
        return Integer.parseInt(string);
    }

    public static int parseInt(String string){
        return Integer.parseInt(string);
    }
}