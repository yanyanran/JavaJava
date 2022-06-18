public class TestMyLinkedList {
    public static void main(String[] args){
        MyLinkedList<String> list = new MyLinkedList<>();

        list.add("AAAA");
        System.out.println("(1)" + list);

        list.add(0,"CCCC");
        System.out.println("(2)" + list);

        list.add("RRRR");
        System.out.println("(3)" + list);

        list.addLast("Ffffff");
        System.out.println("(4)" + list);

        list.add(2,"GHGGGGG");
        System.out.println("(5)" + list);

        list.add(5,"NNNNN");
        System.out.println("(6)" + list);

        list.add(0,"PPPPPP");
        System.out.println("(7)" + list);

        list.remove(0);
        System.out.println("(8)" + list);

        list.remove(2);
        System.out.println("(9)" + list);

        list.remove(list.size() -1);
        System.out.print("(10)" + list + "\n(11)");

        for(String s:list){
            System.out.print(s.toUpperCase() + " ");

            list.clear();
            System.out.println("\nAfter clearing the list, the list size is " + list.size());
        }
    }
}