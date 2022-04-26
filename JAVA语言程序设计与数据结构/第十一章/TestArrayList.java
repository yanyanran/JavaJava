import java.util.ArrayList;

public class TestArrayList {
    public static void main(String[] args){
        ArrayList<String> list = new ArrayList<String>();
        list.add("LLLLLLLLLL");
        list.add("AAAAAAA");
        list.set(3,"OOOOO"); //1
        System.out.println(list.get(3)); //1
    }
}