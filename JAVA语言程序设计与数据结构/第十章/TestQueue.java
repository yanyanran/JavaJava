class Queue{
    private int[] element;
    private int size;
    public static final int DEFAULT_CAPACITY = 8;

    public Queue(){
        this(DEFAULT_CAPACITY);
    }

    public Queue(int capacity){
        element = new int[capacity];
    }

    public void enqueue(int value){
        if(size >= element.length){
            int[] temp = new int[element.length * 2];
            System.arraycopy(element, 0, temp, 0, element.length);
            element = temp;
        }
        element[size] = value;
        size++;
    }

    public int dequeue(){
        int key = element[0];
        for(int i = 0; i < size; i++){
            element[i] = element[i+1];
        }
        size--;
        return key;
    }

    public boolean empty(){
        return size == 0;
    }
    public int getSize(){
        return size;
    }
}
public class TestQueue {
    public static void main(String[] args){
        Queue queue = new Queue();
        for(int i = 0; i < 20; i++){
            queue.enqueue(i);
        }
        while(!queue.empty()){
            System.out.print(queue.dequeue() + " ");
        }
    }
}