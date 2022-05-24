import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Child {
    private Integer id;
    private Integer age;

    public Child() {
    }

    public Child(Integer id, Integer age) {
        this.id = id;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Child{" +
                "id=" + id +
                ", age=" + age +
                '}';
    }
}

public class TestComparator2 {
    public static void main(String[] args) {
        Child child1 = new Child(1, 14);
        Child child2 = new Child(2, 12);
        Child child3 = new Child(3, 10);

        List<Child> list = new ArrayList<>();
        list.add(child1);
        list.add(child2);
        list.add(child3);

        Collections.sort( list, new Comparator<Child>() { // 匿名类对象当做参数传递
            @Override  // 在类外重写compare方法
            public int compare(Child o1, Child o2) {
                return  o1.getAge() > o2.getAge() ? 1 : (o1.getAge() == o2.getAge() ? 0 : -1);
            }
        });

        list.stream().forEach(System.out::println);
    }
}
