import java.util.HashSet;

public class TestAll {
    public static void main(String[] args){
        HashSet<String> set1 = new HashSet<>();
        HashSet<String> set2 = new HashSet<>();
        set1.add("Yellow");
        set1.add("blue");
        set1.add("Green");

        set2.add("Yellow");
        set2.add("blue");
        set2.add("Red");

        set1.addAll(set2);
        /*
        set1: [Red, blue, Yellow, Green]
        set2: [Red, blue, Yellow]
        * */

        set1.removeAll(set2);
        /*
        set1: [Green]
        set2: [Red, blue, Yellow]
        * */

        set1.remove(set2);
        /*
        set1: [blue, Yellow, Green]
        set2: [Red, blue, Yellow]
        * */

        set1.retainAll(set2);
        /*
        set1: [blue, Yellow]
        set2: [Red, blue, Yellow]
        * */

        set1.clear();
        /*
        set1: []
        * */

        System.out.println("set1: " + set1);
        System.out.println("set2: " + set2);
    }
}