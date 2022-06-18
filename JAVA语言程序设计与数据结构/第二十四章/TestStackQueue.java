import java.util.ArrayList;
import java.util.LinkedList;

// 创建链表存储队列中的元素
class GenericQueue<E> {
    private LinkedList<E> list = new LinkedList<>();

    public void enqueue(E e) {  // add
        list.addLast(e);
    }

    public E dequeue() {  // remove
        return list.removeFirst();
    }

    public int getSize() {  // get size
        return list.size();
    }

    @Override
    public String toString(){  //get string all
        return "Queue: " + list.toString();
    }
}

// 创建链表存储栈类中的元素
class GenericStack<T> {
    private LinkedList<T> stack = new LinkedList<T>();
    private int top = 0;

    public int size () {
        return top;
    }

    public void push (T item) {
        stack.add (top++, item);
    }

    public T pop () {
        return stack.remove (--top);
    }
}

public class TestStackQueue{
    public static void main(String[] args){
        // create a stack
        GenericStack<String> stack = new GenericStack<>();

        stack.push("Tom");
        System.out.println("(1)" + stack);

        stack.push("Susan");
        System.out.println("(2)" + stack);

        stack.push("Meredith");
        stack.push("Mike");
        System.out.println("(3)" + stack);

        System.out.println("(4)" + stack.pop());
        System.out.println("(5)" + stack.pop());
        System.out.println("(6)" + stack);

        // create a queue
        GenericQueue<String> queue = new GenericQueue<>();

        queue.enqueue("Tom");
        System.out.println("(7)" + queue);

        queue.enqueue("Susan");
        System.out.println("(8)" + queue);

        queue.enqueue("Meredith");
        queue.enqueue("Mike");
        System.out.println("(9)" + queue);

        System.out.println("(10)" + queue.dequeue());
        System.out.println("(11)" + queue.dequeue());
        System.out.println("(12)" + queue);
    }
}