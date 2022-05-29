import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class TestTreeSet {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();

        set.add("China");
        set.add("Japan");
        set.add("United States");
        set.add("New York");
        set.add("Paris");
        set.add("New York");

        TreeSet<String> treeSet = new TreeSet<>(set);
        System.out.println("tree set is " + treeSet);

        System.out.println("first(): " + treeSet.first());
        System.out.println("last(): " + treeSet.last());
        System.out.println("headSet:(NewYork) " + treeSet.headSet("New York"));
        System.out.println("tailSet:(NewYork) " + treeSet.tailSet("New York"));

        System.out.println("lower(P): " + treeSet.lower("P"));
        System.out.println("higher(P): " + treeSet.higher("P"));
        System.out.println("floor(P): " + treeSet.floor("P"));
        System.out.println("ceiling(P): " + treeSet.ceiling("P"));
        System.out.println("pollFirst(P): " + treeSet.pollFirst());
        System.out.println("pollLast(P): " + treeSet.pollLast());
        System.out.println("new tree set: " + treeSet);
    }
}