import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public abstract class BlockingQueueWithLock<E> implements Queue<E> {

    private E[] array;
    private int head;
    private int tail;

    private volatile int size;

    private ReentrantLock  lock     = new ReentrantLock();
    private Condition notFull  = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public BlockingQueueWithLock(int capacity) {
        array = (E[]) new Object[capacity];
    }

    public void put(E e) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            // 队列满，阻塞
            while (size == array.length) {
                notFull.await();
            }
            array[tail] = e;
            if (  tail == array.length) {
                tail = 0;
            }
            size++;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }
    
    public E take() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            // 队列空，阻塞
            while (isEmpty()) {
                notEmpty.await();
            }
            E element = array[head];
            if (  head == array.length) {
                head = 0;
            }
            --size;
            // 通知isFull条件队列有元素出去
            notFull.signal();
            return element;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        lock.lock();
        try {
            return size == 0;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int size() {
        lock.lock();
        try {
            return size;
        } finally {
            lock.unlock();
        }
    }

}