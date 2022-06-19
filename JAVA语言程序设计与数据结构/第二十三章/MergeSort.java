public class MergeSort {
    public static void mergeSort(int[] list){
        if(list.length > 1) {
            // list前半部分副本 --> firstHalf
            int[] firstHalf = new int[list.length / 2];
            System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
            mergeSort(firstHalf);  // 递归

            int secondHalfLength = list.length - list.length / 2;
            // list后半部分副本 --> secondHalf
            int[] secondHalf = new int[secondHalfLength];
            System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalf.length);
            mergeSort(secondHalf);  // 递归*2

            merge(firstHalf, secondHalf, list); // 两副本排好序后归并成新数组list即可
        }
    }

    // merge方法归并两个数组为temp
    public static void merge(int[] list1, int[] list2, int[] temp){
        int current1 = 0;  // list1 元素
        int current2 = 0;  // list2 元素
        int current3 = 0;  // temp 元素

        while(current1 < list1.length && current2 < list2.length){
            if(list1[current1] < list2[current2]){
                temp[current3++] = list1[current1++]; // 小元素在list1中
            }else{
                temp[current3++] = list2[current2++]; // 小元素在list2中
            }
        }

        while(current1 < list1.length){
            temp[current3++] = list1[current1++];  // list1中有未移元素 --> temp
        }
        while(current2 < list2.length){
            temp[current3++] = list2[current2++];  // list2中有未移元素 --> temp
        }
    }

    public static void main(String[] args){
        int[] list = {2,3,1,-4,-7,9,5,12,0};
        mergeSort(list);
        for (int j : list) {
            System.out.print(j + " ");
        }
    }
}

/*
-7 -4 0 1 2 3 5 9 12
*/
