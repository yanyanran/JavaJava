public class MyLinkedList<E> implements MyList<E>{
    private Node<E> head,tail;
    private int size = 0;

    private static class Node<E>{
        E element;
        Node<E> next;

        public Node(E e){
            this.element = e;
        }
    }

    public MyLinkedList(){
    }

    public MyLinkedList(E[] objects) {
        for(int i = 0; i < objects.length; i++){
            add(objects[i]);
        }
    }

    public E getFirst(){
        if(size == 0){
            return null;
        }else{
            return head.element;
        }
    }

    public E getLast(){
        if(size == 0){
            return null;
        }else{
            return tail.element;
        }
    }

    public void addFirst(E e){
        Node<E> newNode = new Node<E>(e);

        if(tail == null){
            head = tail = newNode;
        }else{
            tail.next = newNode;
            tail = newNode;
        }
        size++;
  }

    public void addLast(E e){
        Node<E> newNode = new Node<>(e);

        if(tail == null){
            head = tail = newNode;
        }else{
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void add(int index, E e){
        if(index == 0){
            addFirst(e);
        }else if(index >= size){
            addLast(e);
        }else{
            Node<E> current = head;
            for(int i = 1; i < index;i++){
                current = current.next;
            }
            Node<E> temp = current.next;
            current.next = new Node<>(e);
            (current.next).next = temp;
            size++;
        }
    }

    public E removeFirst() {
        Node<E> temp;
        if (size == 0) {
            return null;
        } else {
            temp = head;
            head = head.next;
            size--;
            if (head == null) {
                tail = null;
            }
        }
        return temp.element;
    }

    public E removeLast(){
        if(size == 0){
            return null;
        }else if(size == 1){
            Node<E> temp = head;
            head = tail = null;
            size = 0;
            return temp.element;
        }else{
            Node<E> current = head;
            for(int i = 0;i <size - 2;i++){
                current = current.next;
            }
            Node<E> temp = tail;
            tail = current;
            tail.next = null;
            size--;
            return temp.element;
        }
    }

    public E remove(int index){
        if(index < 0 || index >= size){
            return null;
        }else if(index == 0){
            return removeFirst();
        }else if(index == size-1){
            return removeLast();
        }else{
            Node<E> previous = head;
            for(int i = 1;i < index;i++){
                previous = previous.next;
            }
            Node<E> current = previous.next;
            previous.next = current.next;
            size--;
            return current.element;
        }
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder("[");

        Node<E> current = head;
        for(int i = 0;i < size;i++){
            result.append(current.element);
            current = current.next;
            if(current != null){
                result.append(",");
            }else{
                result.append("]");
            }
        }
        return result.toString();
    }

    @Override
    public void clear(){
        size = 0;
        head = tail = null;
    }

    @Override
    public boolean contains(Object e){
        return true;
    }

    @Override
    public E get(int index){
        return null;
    }

    @Override
    public int indexOf(Object e){
        return 0;
    }

    @Override
    public int lastIndexOf(E e){
        return 0;
    }

    @Override
    public E set(int index, E e){
        return null;
    }

    @Override
    public java.util.Iterator<E> iterator(){
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements java.util.Iterator<E>{
        private Node<E> current = head;

        @Override
        public boolean hasNext(){
            return (current != null);
        }

        @Override
        public E next(){
            E e = current.element;
            current = current.next;
            return e;
        }

        @Override
        public void remove(){

        }
    }

    @Override
    public int size(){
        return size;
    }
}
