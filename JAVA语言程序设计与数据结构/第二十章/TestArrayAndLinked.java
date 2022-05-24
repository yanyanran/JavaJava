import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class TestArrayAndLinked {
    public static void main(String[] args) {
        //数组线性表
        List<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(1);
        arrayList.add(4);
        arrayList.add(0, 10);
        arrayList.add(3, 30);

        System.out.println("A list of integers in the array list: ");
        System.out.println(arrayList); // [10, 1, 2, 30, 3, 1, 4]

        //线性表链表结构
        LinkedList<Object> linkedList = new LinkedList<Object>(arrayList);
        linkedList.add(1, "red");
        linkedList.removeLast();
        linkedList.addFirst("green");

        System.out.println("Display the linked list forward: ");
        //迭代器遍历操作
        ListIterator<Object> listIterator = linkedList.listIterator();
        while(listIterator.hasNext()) { // 顺序遍历打印
            System.out.print(listIterator.next() + " "); // green 10 red 1 2 30 3 1
        }

        System.out.println("Display the linked list backward: ");
        while(listIterator.hasPrevious()){ // 逆序遍历打印
            System.out.print(listIterator.previous() + " "); // 1 3 30 2 1 red 10 green
        }
    }
}