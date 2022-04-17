class StackOfIntegers{
    private int[] elements;  //栈中元素存储
    private int size;
    public static final int DEFAULT_CAPACITY = 16; //final关键字表示不能更改

    // 构建一个默认容量为16的空栈
    public StackOfIntegers(){
        //elements = new int[16];
        this(DEFAULT_CAPACITY);
    }

    // 构建一个指定容量的空栈
    public StackOfIntegers(int capacity){
        elements = new int[capacity];
    }

    // 如果栈为空返回true
    public boolean empty(){
        /*
        if(size == 0){
            return true;
        }else return false;
        */
        return size == 0;
    }

    // 返回栈中元素个数
    public int getSize(){
        return size;
    }

    // 将一个整数存到栈顶
    public void push(int value){
        if( size >= elements.length ){ // 如果栈满
            int[] temp = new int[elements.length * 2];  // 创建一个当前容量二倍的新数组
            System.arraycopy(elements, 0, temp, 0, elements.length); // 将当前数组内容复制到新数组中
            elements = temp;  // 将新数组的引用赋值给栈中当前数组
        }
        elements[size++] = value; // 添加新值
    }

    // 返回栈顶的整数而不从栈中删除它
    public int peek(){
        return elements[size];
    }

    // 删除栈顶整数并返回它
    public int pop(){
        /*
        int num = elements[size];
        elements[size] = 0;
        return num;
        */
        return elements[--size];
    }
}

//Test
public class TestStackOfIntegers {
    public static void main(String[] args){
        StackOfIntegers stack = new StackOfIntegers();

        for(int i = 0; i < 10; i++){
            stack.push(i);
        }
        while(!stack.empty()){
            System.out.print(stack.pop() + " ");
        }
    }
}
/* 9 8 7 6 5 4 3 2 1 0 */