public class RadixSort {
    // 获取数组a中最大值
    private static int getMax(int[] a) {
        int max;

        max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max){
                max = a[i];
            }
        }
        return max;
    }

    // 对数组按照"某个位数"进行排序(桶排序)
    private static void countSort(int[] a, int exp) {
        int[] output = new int[a.length];    // 存储"被排序数据"的临时数组
        int[] buckets = new int[10];

        // 将数据出现的次数存储在buckets[]中
        for (int i = 0; i < a.length; i++) {
            buckets[(a[i] / exp) % 10]++;
        }

        // 让更改后buckets[i]的值是该数据在output[]中的位置
        for (int i = 1; i < 10; i++) {
            buckets[i] += buckets[i - 1];
        }

        // 将数据存储到临时数组output[]中
        for (int i = a.length - 1; i >= 0; i--) {
            output[buckets[ (a[i]/exp)%10 ] - 1] = a[i];
            buckets[ (a[i]/exp)%10 ]--;
        }

        // 将排序好的数据赋值给a[]
        for (int i = 0; i < a.length; i++) {
            a[i] = output[i];
        }
        output = null;
        buckets = null;
    }

    // 基数排序
    public static void radixSort(int[] a) {
        int exp;    // 指数。当对数组按各位进行排序时，exp=1；按十位进行排序时，exp=10
        int max = getMax(a);    // 数组a中的最大值

        // 从个位开始，对数组a按"指数"进行排序
        for (exp = 1; max/exp > 0; exp *= 10)
            countSort(a, exp);
    }

    public static void main(String[] args) {
        int i;
        int a[] = {53, 3, 542, 748, 14, 214, 154, 63, 616};
        radixSort(a);    // 基数排序
        for (i=0; i<a.length; i++) {
            System.out.printf("%d ", a[i]);
        }
    }
}
