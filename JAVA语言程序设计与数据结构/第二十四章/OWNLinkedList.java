public class OWNLinkedList {

    /**
     *  Simple version
     * */

    // Node's class
    public class Node<E>{
        E element;
        Node<E> next;

        public Node(E e){
            this.element = e;
        }
    }

    private Node<E> head,tail;
    private int size = 0;

    public void addFirst(E e){
        Node<E> node = new Node<>(e);
        node.next = head;
        head = node;
        size++;
        if(tail == null){
            tail = head;
        }
    }

    public void addLast(E e){
        Node<E> node = new Node<>(e);
        if(tail == null){
            head = tail = node;
        }else{
            tail.next = node;
            tail =node;
        }
        size++;
    }

    public void add(int index, E e){
        Node<E> current = head;
        for(int i = 1;i < index; i++){
            current = current.next;
        }
        Node<E> temp = current.next;
        current.next = new Node<>(e);
        (current.next).next = temp;
        size++;
    }

    public E removeFirst(){
        Node<E> temp;
        temp = head;
        head = head.next;
        size--;
        return temp.element;
    }

    public E removeLat(){
        Node<E> current = head;
        for(int i = 0; i < size - 2;i++){
            current = current.next;
        }
        Node<E> temp = tail;
        tail = current;
        tail.next = null;
        size--;
        return temp.element;
    }

    public E remove(int index){
        Node<E> previous = head;
        for(int i = 1; i < size ; i++){
            previous = previous.next;
        }
        Node<E> current = previous.next;
        previous.next = current.next;
        size--;
        return current.element;
    }
}