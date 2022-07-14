import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MainBlockingQueue<E> {
    public Object[] elements;

    private int head = 0,tail = 0;
    private int size;

    private ReentrantLock lock =new ReentrantLock();
    private Condition notEmpty=lock.newCondition();
    private Condition notFull =lock.newCondition();

    public void BlockingQueue(int capacity){
        this.elements = new Object[capacity];
    }

    public void put(E e){
        lock.lock();
        try{
            while (size== elements.length)
                notFull.await();
            elements[tail] =e;
            if (++tail == elements.length) {
                tail=0;
            }
            size++;
            notEmpty.signal();
        }catch (InterruptedException ex) {
            ex.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

     //take
    public E take() {
        lock.lock();
        E e =null;
        try{
            while (size == 0) {
                notEmpty.await();
            }
            e=(E)elements[head];
            elements[head]=null;
            if(++head == elements.length){
                head=0;
            }
            size--;
            notEmpty.signal();
        }catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        finally {
            lock.unlock();
        }
        return e;
    }
}