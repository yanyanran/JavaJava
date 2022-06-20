package QuitSort;

class QuickSort {
    // 常规元素
    public static void quickSort(int[] list) {
        quickSort(list, 0, list.length - 1);
    }

    // 特定元素
    public static void quickSort(int[] list, int first, int last) {
        if (last > first) {
            int pivotIndex = partition(list, first, last);
            quickSort(list, first, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, last);
        }
    }

    // 方法用low和high分别指向数组两端。
    public static int partition(int[] list, int first, int last) {
        int pivot = list[first];
        int low = first + 1;  // low指向数组第二个元素
        int high = last; // high指向最后一个元素

        // 在数组左逐一寻找 > pivot 的元素，在右逐一寻找第一个 <= pivot 的元素。然后将他俩交换
        while (high > low) {
            while (low <= high && list[low] <= pivot) {
                low++;
            }
            while (low <= high && list[high] > pivot) {
                high--;
            }

            if (high > low) {
                int temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }
        while (high > first && list[high] >= pivot) {
            high--;
        }

        if (pivot > list[high]) {  // 如果基准元素移动
            list[first] = list[high];
            list[high] = pivot;
            return high; // 返回新pivot下标
        } else return first;  // 否则返回初始pivot下标
    }

    // Test
    public static void main(String[] args) {
        int[] list = {2, 7, 5, 66, 44, 34, -22, -1, 0, 6};
        quickSort(list);
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i] + " ");
        }
    }
}