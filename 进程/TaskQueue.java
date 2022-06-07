import java.util.LinkedList;
import java.util.Queue;

class TaskQueue {
    Queue<String> queue = new LinkedList<>();

    public synchronized void addTask(String s){

        this.queue.add(s);
    }

    public synchronized String getTask() throws InterruptedException{
        while (queue.isEmpty()){
            this.wait();
        }
        return queue.remove();
    }
}

//线程1可以调用addTask()不断往队列中添加任务；
//线程2可以调用getTask()从队列中获取任务。如果队列为空，则getTask()应该等待，直到队列中至少有一个任务时再返回。

// 本意：getTask()内部先判断队列是否为空，如果为空，就循环等待，直到另一个线程往队列中放入了一个任务，while()循环退出，就可以返回队列的元素了。

//结果：while循环永远不会退出。因为线程在执行while循环的时候已经在getTask入口获取了this锁，所以线程根本无法调用 addTask，因为addTask执行条件也是获取this锁