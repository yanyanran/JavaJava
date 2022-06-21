public class Heap<E extends Comparable<E>>{
    // 堆在内部用数组线性表表示
    private java.util.ArrayList<E> list = new java.util.ArrayList<>();

    // 创建一个默认空堆
    public Heap(){
    }

    // 创建一个具有指定对象的堆
    public Heap(E[] objects){
        for(int i = 0; i < objects.length; i++){
            add(objects[i]);
        }
    }

    // 添加一个新的对象到堆中
    public void add(E newObject){
        list.add(newObject);
        int currentIndex = list.size() - 1;

        while(currentIndex > 0){
            int parentIndex = (currentIndex - 1) / 2;
            if(list.get(currentIndex).compareTo(
                    list.get(parentIndex)) > 0){  // 当前结点 > 父结点
                // 交换current结点和parent结点
                E temp = list.get(currentIndex);
                list.set(currentIndex, list.get(parentIndex));  // 使用set()替换
                list.set(parentIndex,temp);
            }else break;
            currentIndex = parentIndex;  // 让当前结点指向父结点
        }
    }

    // 将根结点从堆中删除并返回
    public E remove(){
        if(list.size() == 0){
            return null;
        }
        E removeObject = list.get(0);
        list.set(0, list.get(list.size() - 1));  // 用最后一个结点替换当前根结点
        list.remove(list.size() - 1);  // 删掉最后一个结点

        int currentIndex = 0;  // 从根开始
        // 确定maxIndex
        while(currentIndex < list.size()){
            int leftChildIndex = 2 * currentIndex + 1;
            int rightChildIndex = 2 * currentIndex + 2;
            if(leftChildIndex >= list.size()){
                break;
            }
            int maxIndex = leftChildIndex;
            if(rightChildIndex < list.size()){
                if(list.get(maxIndex).compareTo(
                        list.get(rightChildIndex)) < 0){  // 比较左右谁更大
                    maxIndex = rightChildIndex;
                }
            }

            if(list.get(currentIndex).compareTo(
                    list.get(maxIndex)) < 0) {  // 当前结点 < 较大子结点
                // 交换
                E temp = list.get(maxIndex);
                list.set(maxIndex, list.get(currentIndex));
                list.set(currentIndex, temp);
                currentIndex = maxIndex;
            }else break;
        }
        return removeObject;
    }

    // 返回堆的大小
    public int getSize() {
        return list.size();
    }
}