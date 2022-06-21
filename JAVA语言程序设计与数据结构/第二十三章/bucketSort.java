import java.util.ArrayList;
import java.util.Collections;

public class bucketSort {
    public static void bucketSort(int[] arr) {

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }

        //桶数
        int bucketNum = (max - min) / arr.length + 1;
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketNum);
        for (int i = 0; i < bucketNum; i++) {
            bucketArr.add(new ArrayList<Integer>());
        }

        //将每个元素放入桶
        for (int i = 0; i < arr.length; i++) {
            int num = (arr[i] - min) / (arr.length);
            bucketArr.get(num).add(arr[i]);
        }

        //对每个桶进行排序
        for (int i = 0; i < bucketArr.size(); i++) {
            Collections.sort(bucketArr.get(i));
        }
        System.out.println(bucketArr.toString());
    }

    public static void bucketSort(int[] a, int max) {
        int[] buckets;

        if (a==null || max<1)
            return ;

        // 创建一个容量为max的数组buckets，并且将buckets中的所有数据都初始化为0。
        buckets = new int[max];

        // 1. 计数
        for(int i = 0; i < a.length; i++)
            buckets[a[i]]++;

        // 2. 排序
        for (int i = 0, j = 0; i < max; i++) {
            while( (buckets[i]--) >0 ) {
                a[j++] = i;
            }
        }
        buckets = null;
    }

    public static void main(String[] args) {
        int[] list = {1, 0, 2, 2, 6, 4, 2, 3, -1, -4, -1};
        bucketSort(list);
    }
}
