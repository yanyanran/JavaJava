public class GenericSort {
    public static void main(String[] args){
        Integer[] intArray = {new Integer(2),new Integer(4),new Integer(3)};

        Double[] doubleArray = {new Double(3.4),new Double(1.3),new Double(-22.1)};

        Character[] charArray = {new Character('a'),new Character('J'),new Character('r')};

        String[] stringArray = {"Tom", "Susan", "Kim"};

        sort(intArray);
        sort(doubleArray);
        sort(charArray);
        sort(stringArray);

        printList(intArray);
        printList(doubleArray);
        printList(charArray);
        printList(charArray);
    }

    //泛型方法的泛型类型继承接口Comparable，后期可以调用compareTo方法
    public static <E extends Comparable<E>> void sort(E[] list){
        E currentMin;
        int currentMinIndex;

        for(int i =0; i< list.length; i++){
            currentMin = list[i];
            currentMinIndex = i;

            for(int j = i + 1;j < list.length;j++){
                //确定元素位置
                if(currentMin.compareTo(list[j]) > 0){
                    currentMin = list[j];
                    currentMinIndex = j;
                }
            }
            if(currentMinIndex != i){
                list[currentMinIndex] = list[i];
                list[i] = currentMin;
            }
        }
    }
    public static void printList(Object[] list){
        for(int i = 0; i < list.length; i++){
            System.out.print(list[i] + " ");
        }
        System.out.println();
    }
}