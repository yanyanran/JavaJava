import java.util.Collection;
import java.util.Iterator;

public class MyArrayList<E> implements MyList<E>{
    public static final int INITIAL_CAPACITY = 16;  // 常量用于创建一个初始数组
    // 泛型限制处理：
    private E[] data = (E[])new Object[INITIAL_CAPACITY];  // 直接创建Object型数组，后强转为E[]型数组即可
    private int size = 0;  // 跟踪线性表元素个数

    public MyArrayList(){
    }

    public MyArrayList(E[] object){
        for(int i = 0; i < object.length; i++){
            add(object[i]);
        }
    }

    // 元素e插入指定下标index处
    @Override
    public void add(int index,E e){
        if(index < 0 || index >size){
            throw new IndexOutOfBoundsException("Index: " + index + " ,Size: " + size);
        }
        ensureCapacity();
        for(int i = size - 1; i >= index; i--){
            data[i+1] = data[i];
        }
        data[index] = e;
        size++;
    }

    // 检验数组是否已满
    private void ensureCapacity(){
        if(size >= data.length){
            E[] newData = (E[])(new Object[size * 2 + 1]);
            System.arraycopy(data, 0, newData, 0,size);  // 复制数组
            data = newData;
        }
    }

    // 创建大小为常量的新数组，size设置为0
    @Override
    public void clear(){
        // 这行删掉，能跑，但会内存泄露。
        // 只有创建了新数组并赋给data，原来data的内容才会被当作垃圾回收掉
        data = (E[])new Object[INITIAL_CAPACITY];
        size = 0;
    }

    // 判读数组中是否包含元素e
    @Override
    public boolean contains(Object e){
        for(int i = 0; i < size; i++){
            if(e.equals(data[i])){ // equals比较
                return true;
            }
        }
        return false;
    }

    // 检查索引index是否在范围内，yes --> 返回索引处元素
    @Override
    public E get(int index){
        checkIndex(index);
        return data[index];
    }

    // 检查索引index是否在范围内，no --> 抛出异常
    private void checkIndex(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    // 从头比较
    @Override
    public int indexOf(Object e){
        for(int i = 0; i < size; i++){
            if(e.equals(data[i])){
                return i;
            }
        }
        return -1;
    }

    // 从尾比较
    @Override
    public int lastIndexOf(E e){
        for(int i = size - 1; i >= 0; i--){
            if(e.equals(data[i])){
                return i;
            }
        }
        return -1;
    }

    // 删除操作
    @Override
    public E remove(int index){
        checkIndex(index); // 检查
        E e = data[index];
        for(int j = index; j< size -1; j++){
            data[j] = data[j + 1];  // 索引之后的元素向左移动
        }
        data[size -1] = null;  // 最后一个置为null
        size--;  // 个数-1
        return e;
    }

    // 替换
    @Override
    public E set(int index,E e){
        checkIndex(index);
        E old = data[index];
        data[index] = e;
        return old;
    }

    // 返回线性表中所有元素的字符串
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder("[");
        for(int i = 0; i < size; i++){
            result.append(data[i]);
            if(i < size - 1){
                result.append(", ");
            }
        }
        return result.toString() + "]";
    }

    // size与capacity不与相等时，可用于修剪数组大小
    public void trimToSize(){
        if(size != data.length){
            E[] newData = (E[])(new Object[size]);
            System.arraycopy(data, 0, newData, 0,size);
            data = newData;
        }
    }

    // 迭代器迭代器
    // 返回一个java.util.Iterator实例
    @Override
    public Iterator<E> iterator(){
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<E>{
        private int current = 0;  // 标识被遍历元素的当前位置

        @Override
        public boolean hasNext(){
            return current < size;
        }
        @Override
        public E next(){
            return data[current++];
        }
        @Override
        public void remove(){
            if(current == 0){
                throw new IllegalStateException();
            }
            MyArrayList.this.remove(--current);
        }
    }

    // 返回元素个数
    @Override
    public int size(){
        return size;
    }
}