import java.util.Queue;

//synchronized
public abstract class BlockingQueueWithSyn<E> implements Queue<E> {

    private E[] array;
    private int head;  // 队头指针
    private int tail;  // 队尾指针

    private volatile int size; // 队列元素个数

    public BlockingQueueWithSyn(int capacity) {
        array = (E[]) new Object[capacity];
    }

    public synchronized void put(E e) throws InterruptedException {
        // 当队列满的时候阻塞
        while (size == array.length) {
            this.wait();
        }

        array[tail] = e;
        // 队列装满后索引归零
        if (  tail == array.length) {
            tail = 0;
        }
        size++;
        // 通知其他消费端有数据了
        this.notifyAll();
    }

    public synchronized E take() throws InterruptedException {
        // 当队列空的时候阻塞
        while (isEmpty()) {
            this.wait();
        }

        E element = array[head];
        // 消费完后从0开始
        if (  head == array.length) {
            head = 0;
        }
        --size;
        // 通知其他生产者可以生产了
        this.notifyAll();
        return element;
    }

    @Override
    public synchronized boolean isEmpty() {
        return size == 0;
    }

    @Override
    public synchronized int size() {
        return size;
    }
}