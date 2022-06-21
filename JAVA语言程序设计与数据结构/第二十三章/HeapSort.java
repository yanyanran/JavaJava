public class HeapSort {
    public static <E extends Comparable<E>> void heapSort(E[] list) {
        Heap<E> heap = new Heap<>();

        for(int i = 0; i < list.length; i++) {
            heap.add(list[i]);
        }

        for(int i = list.length - 1; i >= 0; i--) {
            list[i] = heap.remove();
        }
    }

    public static void main(String[] args){
        Integer[] list = {-44,67,89,21,100,7,5,32,2};
        heapSort(list);
        for(int i = 0; i < list.length; i++) {
            System.out.print(list[i] + " ");
        }
    }
}