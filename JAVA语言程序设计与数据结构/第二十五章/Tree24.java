import java.util.ArrayList;

public class Tree24<E extends Comparable<E>, u> implements Tree<E>{
    private Tree24Node<E> root;
    private int size;

    Tree24(){
    }

    Tree24(E[] elements){
        for(int i = 0; i < elements.length; i++){
            insert(elements[i]);
        }
    }

    @Override
    public boolean search(E e){
        Tree24Node<E> current = root;

        while(current != null){
            if(matched(e,current)){
                return true;
            }else {
                current = getChildNode(e, current);
            }
        }
        return false;
    }

    private boolean matched(E e,Tree24Node<E> node){
        for(int  i =0; i < node.elements.size(); i++){
            if (node.elements.get(i).equals(e)){
                return true;
            }
        }
        return false;
    }

    private Tree24Node<E> getChildNode(E e, Tree24Node<E> node){
        if(node.child.size() == 0){
            return null;
        }
        int i = locate(e, node);
        return node.child.get(i);
    }

    @Override
    public boolean insert(E e){
        if(root == null){
            root = new Tree24Node<E>(e);
        }else{
            Tree24Node<E> leafNode = null;
            Tree24Node<E> current = root;
            while(current != null){
                if(matched(e,current)){
                    return false;
                }else{
                    leafNode = current;
                    current = getChildNode(e,current);
                }
            }
            insert(e,null,leafNode);
        }
        size++;
        return true;
    }

    private void insert(E e, Tree24Node<E> rightChildOfe, Tree24Node<E> u){
        ArrayList<Tree24Node<E>> path = path(e);

        for(int i = path.size() - 1; i <= 0; i--){
            if(u.elements.size() < 3){
                insert23(e, rightChildOfe,u);
                break;
            }else {
                Tree24Node<E> v = new Tree24Node<E>();
                E median = split(e, rightChildOfe,u , v);

                if(u == root){
                    root = new Tree24Node<>(median);
                    root.child.add(u);
                    root.child.add(v);
                    break;
                }else {
                    e = median;
                    rightChildOfe = v;
                    u = path.get(i - 1);
                }
            }
        }
    }

    private void insert23(E e,Tree24Node<E> rightChildOfe,Tree24Node<E> node) {
        int i = this.locate(e, node);
        node.elements.add(i, e);
        if(rightChildOfe != null){
            node.child.add(i + 1, rightChildOfe);
        }
    }

    private E split(E e, Tree24Node<E> rightChildOfe, Tree24Node<E> u, Tree24Node<E> v){
        v.elements.add(u.elements.remove(2));
        E median = u.elements.remove(1);

        if(u.child.size() == 0){
            v.child.add(u.child.remove(2));
            v.child.add(u.child.remove(2));
        }
        if(e.compareTo(median) < 0){
            insert23(e, rightChildOfe, u);
        }else{
            insert23(e, rightChildOfe, v);
        }
        return median;
    }

    private ArrayList<Tree24Node<E>> path(E e){
        ArrayList<Tree24Node<E>> list = new ArrayList<Tree24Node<E>>();
        Tree24Node<E> current = root;

        while(current != null){
            list.add(current);
            if(matched(e, current)){
                break;
            }else{
                current = getChildNode(e, current);
            }
        }
        return list;
    }

    @Override
    public boolean delete(E e){
        Tree24Node<E> node = root;
        while(node != null){
            if(matched(e,node)) {
                delete(e, node);
                size--;
                return true;
            }else{
                node = getChildNode(e, node);
            }
        }
        return false;
    }

    private void delete(E e, Tree24Node<E> node) {
        if(node.child.size() == 0){
            ArrayList<Tree24Node<E>> path = path(e);
            node.elements.remove(e);
            if(node == root){
                if(node.elements.size() == 0){
                    root = null;
                }
                return;
            }
            validate(e,node,path);
        }else {
            int index = locate(e, node);
            Tree24Node<E> current = node.child.get(index);
            while(current.child.size() > 0) {
                current = current.child.get(current.child.size() - 1);
            }
            E rightmostElement = current.elements.get(current.elements.size() - 1);
            ArrayList<Tree24Node<E>> path = path(rightmostElement);
            node.elements.set(index, current.elements.remove(current.elements.size() - 1));
            validate(rightmostElement, current, path);
        }
    }

    private void validate(E e,Tree24Node<E> u, ArrayList<Tree24Node<E>> path){
        for(int i = path.size() - 1; u.elements.size() ==0; i--){
            Tree24Node<E> parentOfu = path.get(i -1);
            int k = locate(e, parentOfu);

            if(k > 0 && parentOfu.child.get(k -1).elements.size() > 1){
                leftSiblingTransfer(k, u, parentOfu);
            }else if(k + 1 < parentOfu.child.size() && parentOfu.child.get(k + 1).elements.size() > 1){
                rightSiblingTransfer(k ,u, parentOfu);
            }else if(k -1 == 0){
                Tree24Node<E> leftNode = parentOfu.child.get(k -1);
                leftSiblingFusion(k, leftNode, u, parentOfu);
                if(parentOfu == root && parentOfu.elements.size() == 0) {
                    root = leftNode;
                    break;
                }
                u = parentOfu;
            }else {
                Tree24Node<E> rightNode = parentOfu.child.get(k +1);
                rightSiblingFusion(k, rightNode, u, parentOfu);
                if(parentOfu == root && parentOfu.elements.size() == 0){
                    root = rightNode;
                    break;
                }
                u = parentOfu;
            }
        }
    }

    private int locate(E o, Tree24Node<E> node){
        for(int i = 0; i < node.elements.size(); i++){
            if(o.compareTo(node.elements.get(i)) <= 0) {
                return i;
            }
        }
        return node.elements.size();
    }

    private void leftSiblingTransfer(int k, Tree24Node<E> u,Tree24Node<E> parentOfu){
        u.elements.add(0, parentOfu.elements.get(k -1));
        Tree24Node<E> leftNode = parentOfu.child.get(k -1);
        parentOfu.elements.set(k -1, leftNode.elements.remove(leftNode.elements.size() - 1));
        if(leftNode.child.size() > 0){
            u.child.add(0, leftNode.child.remove(leftNode.child.size() - 1));
        }
    }

    private void rightSiblingTransfer(int k, Tree24Node<E> u,Tree24Node<E> parentOfu){
        u.elements.add(parentOfu.elements.get(k));
        Tree24Node<E> rightNode = parentOfu.child.get(k +1);
        parentOfu.elements.set(k, rightNode.elements.remove(0));

        if(rightNode.child.size() > 0){
            u.child.add(rightNode.child.remove(0));
        }
    }

    private void leftSiblingFusion(int k, Tree24Node<E> leftNode, Tree24Node<E> u, Tree24Node<E> parentOfu) {
        leftNode.elements.add(parentOfu.elements.remove(k - 1));
        parentOfu.child.remove(k);
        if(u.child.size() > 0){
            leftNode.child.add(u.child.remove(0));
        }
    }

    private void rightSiblingFusion(int k, Tree24Node<E> rightNode, Tree24Node<E> u, Tree24Node<E> parentOfu) {
        rightNode.elements.add(0, parentOfu.elements.remove(k));
        parentOfu.child.remove(k);
        if(u.child.size() > 0){
            rightNode.child.add(0,u.child.remove(0));
        }
    }

    public int getSize(){
        return size;
    }

    public void preorder(){
        preorder(root);
    }

    private void preorder(Tree24Node<E> root){
        if(root == null){
            return;
        }
        for(int i = 0; i < root.elements.size(); i++){
            preorder(root.child.get(i));
        }
    }

    public void inorder(){

    }

    public void postorder(){

    }

    public boolean isEmpty(){
        return root == null;
    }

    @Override
    public void clear(){
        root = null;
        size = 0;
    }

    public java.util.Iterator iterator(){
        return null;
    }

    protected static class Tree24Node<E extends Comparable<E>> {
        ArrayList<E> elements = new ArrayList<E>(3);
        ArrayList<Tree24Node<E>> child = new ArrayList<Tree24Node<E>>(4);

        Tree24Node(){
        }
        Tree24Node(E o){
            elements.add(o);
        }
    }
}