import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class TestHashSet {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();

        set.add("LLLondon");
        set.add("BBBBBondon");
        set.add("LLLondon");
        set.add("Aaa");
        set.add("Ring");

        System.out.println(set);
        for(String s : set) {
            System.out.print(s.toUpperCase() + " ");
        }
        System.out.println();
        set.forEach(e -> System.out.print(e.toLowerCase() + " "));
    }
}