public class InsertionSort {
    public static void insertionSort(int[] list){
        // 从下标为1的元素开始选择合适位置插入（因为下标为0的只有一个元素，默认是有序的
        for(int i = 1; i < list.length; i++){
            int currentElement = list[i];  // 记录要插入的数据
            int k;
            // 从已经排序的序列最右边的开始比较，找到比其小的数
            for(k = i - 1; k >= 0 && list[k] > currentElement; k--){
                list[k + 1] = list[k];
            }
            // 存在比其小的数，插入
            list[k + 1] = currentElement;
        }
    }
}