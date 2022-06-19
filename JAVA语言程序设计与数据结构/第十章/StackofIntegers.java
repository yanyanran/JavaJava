public class StackofIntegers {
    private int[] elements;
    private int size;
    public static final int DEFAULT_CAPACITY = 16;

    /**
     * 构造一个默认容量为16的堆栈
     */
    public StackofIntegers() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 构造具有指定容量的堆栈
     */
    public StackofIntegers(int capacity) {
        elements = new int[capacity];
    }

    /**
     * 将新整数放入堆栈顶部
     */
    public void push(int value) {
        if (size >= elements.length) {
            int[] temp = new int[elements.length * 2];
            System.arraycopy(elements, 0, temp, 0, elements.length);
            elements = temp;
        }
        elements[size++] = value;
    }

    /**
     * 返回并从堆栈中移除顶部元素
     */
    public int pop() {
        return elements[--size];
    }

    /**
     * 从堆栈返回顶部元素
     */
    public int peek() {
        return elements[size - 1];
    }

    /**
     * 测试堆栈是否为空
     */
    public boolean empty() {
        return size == 0;
    }

    /**
     * 返回堆栈中的元素数
     */
    public int getSize() {
        return size;
    }
}