import java.util.Scanner;

public class PalindromeIgnore {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a string : ");
        String s = input.nextLine();

        System.out.println("忽略非字母或数字字符 \n " + s + "是回文吗?: " + isPalindrome(s));
    }

    public static boolean isPalindrome(String s){
        String s1 = filter(s);
        String s2 = reverse(s1);
        return s2.equals(s1);
    }

    public static String filter(String s){  //逐一检查字符串s中的每个字符，如果字符是数字或字母，就将其复制到字符串构建器中

        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0;i < s.length();i++)
        {
            stringBuilder.append(s.charAt(i));
        }
        return stringBuilder.toString();
    }

    public static String reverse(String s){  //倒置字符串
        StringBuilder stringBuilder = new StringBuilder(s);
        stringBuilder.reverse();
        return stringBuilder.toString();
    }
}