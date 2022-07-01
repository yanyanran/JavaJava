import java.util.ArrayList;

public class RBTree<E extends Comparable<E>> extends BST<E> {
    public RBTree(){
    }

    public RBTree(E[] elements){
        super(elements);
    }

    @Override
    protected RBTreeNode<E> createNewNode(E e){
        return new RBTreeNode<E>(e);
    }

    @Override
    public boolean insert(E e){
        boolean successful = super.insert(e);
        if(!successful){
            return false;
        }else {
            ensureRBTree(e);
        }
        return true;
    }

    private void ensureRBTree(E e){
        ArrayList<TreeNode<E>> path = path(e);
        int i = path.size();
        RBTreeNode<E> u = (RBTreeNode<E>) (path.get(i));
        RBTreeNode<E> v = (u ==root) ? null :(RBTreeNode<E>) (path.get(i -1));
        u.setRed();
        if(u == root){
            u.setBlack();
        }else if(v.isRed()){
            fixDoubleRed(u, v, path, i);
        }
    }

    private void fixDoubleRed(RBTreeNode<E> u, RBTreeNode<E> v, ArrayList<TreeNode<E>> path, int i){
        RBTreeNode<E> w = (RBTreeNode<E>)(path.get(i - 2));
        RBTreeNode<E> parentsOfw = (w == root) ? null : (RBTreeNode<E>)path.get(i - 3);

        RBTreeNode<E> x = (w.left == v) ? (RBTreeNode<E>) (w.right) : (RBTreeNode<E>) (w.left);

        if (x == null || x.isBlack()) {
            if(w.left == v && v.left == u){
                restructureRecolor(u, v , w, w, parentsOfw);

                w.left = v.right;
                v.right = w;
            }else if(w.left == v && v.right == u) {
                restructureRecolor(v, u , w, w, parentsOfw);
                v.right = u.left;
                w.left = u.left;
                w.left = u.right;
                u.left = v;
                u.right = w;
            }else if(w.right == v && v.right == u){
                restructureRecolor(w, v, u ,w, parentsOfw);
                w.right = v.left;
                v.left = w;
            }else{
                restructureRecolor(w, u, v, w, parentsOfw);
                w.right = u.left;
                v.left = u.right;
                u.left = w;
                u.right = v;
            }
        }else{
            w.setRed();
            u.setRed();
            ((RBTreeNode<E>)(w.left)).setBlack();
            ((RBTreeNode<E>)(w.right)).setBlack();

            if(w == root){
                w.setBlack();
            }else if(((RBTreeNode<E>)parentsOfw).isRed()) {
                u = w;
                v = (RBTreeNode<E>) parentsOfw;
                fixDoubleRed(u, v, path, i - 2);
            }
        }
    }

    private void restructureRecolor(RBTreeNode<E> a, RBTreeNode<E> b, RBTreeNode<E> c, RBTreeNode<E> w, RBTreeNode<E> parentsOfw) {
        if(parentsOfw == null){
            root = b;
        }else if(parentsOfw.left == w){
            parentsOfw.left = b;
        }else{
            parentsOfw.right = b;
        }

        b.setBlack();
        a.setRed();
        c.setRed();
    }

    @Override
    public boolean delete(E e){
        TreeNode<E> current = root;
        while(current != null){
            if(e.compareTo(current.element) < 0) {
                current = current.left;
            }else if(e.compareTo(current.element) < 0) {
                current = current.right;
            }else
                break;
        }
        if(current == null){
            return false;
        }
        java.util.ArrayList<TreeNode<E>> path;

        if(current.left != null && current.right != null){
            TreeNode<E> rightMost = current.left;
            while(rightMost.right != null){
                rightMost = rightMost.right;
            }
            path = path(rightMost.element);
            current.element = rightMost.element;
        }else
            path = path(e);

        deleteLastNodeInPath(path);

        size--;
        return true;
    }

    public void deleteLastNodeInPath(ArrayList<TreeNode<E>> path) {
        int i = path.size() -1;

        RBTreeNode<E> u = (RBTreeNode<E>) (path.get(i));
        RBTreeNode<E> parentsOfu = (u == root) ? null : (RBTreeNode<E>) (path.get(i - 1));
        RBTreeNode<E> grandparentOfu = (parentsOfu == null || parentsOfu == root) ? null : (RBTreeNode<E>) (path.get(i - 2));
        RBTreeNode<E> childOfu = (u.left == null) ? (RBTreeNode<E>) (u.right) : (RBTreeNode<E>) (u.left);

        connectNewParent(parentsOfu, u, childOfu);

        if(childOfu == root || u.isRed()){
            return;
        } else if (childOfu != null && childOfu.isRed()) {
            childOfu.setBlack();
        }else {
            fixDoubleBlack(grandparentOfu, parentsOfu, childOfu, path, i);
        }
    }

    private void fixDoubleBlack(RBTreeNode<E> grandparent, RBTreeNode<E> parent, RBTreeNode<E> db, ArrayList<TreeNode<E>> path, int i){
        RBTreeNode<E> y =(parent.right == db) ? (RBTreeNode<E>)(parent.left) : (RBTreeNode<E>) (parent.right);
        RBTreeNode<E> y1 = (RBTreeNode<E>) (y.left);
        RBTreeNode<E> y2 = (RBTreeNode<E>) (y.right);

        if(y.isBlack() && y1 != null && y1.isRed()) {
            if(parent.right == db) {
                connectNewParent(grandparent, parent, y);
                recolor(parent, y, y1);

                parent.left = y.right;
                y.right = parent;
            }else {
                connectNewParent(grandparent, parent, y1);
                recolor(parent, y1, y);

                parent.right = y1.left;
                y.left = y1.right;
                y1.left = parent;
                y1.right = y;
            }
        }else if(y.isBlack() && y2 != null && y2.isRed()) {
            if(parent.right == db){
                connectNewParent(grandparent, parent, y2);
                recolor(parent, y2, y);

                y.right = y2.left;
                parent.left = y2.right;
                y2.left = y;
                y2.right = parent;
            }else {
                connectNewParent(grandparent, parent, y);
                recolor(parent, y, y2);

                y.left = parent;
                parent.right = y1;
            }
        }else if(y.isBlack()) {
            y.setRed();
            if(parent.isRed()){
                parent.setBlack();
            }else if(parent != root){
                db = parent;
                parent = grandparent;
                grandparent = (i >= 3) ? (RBTreeNode<E>) (path.get(i - 3)) : null;
                fixDoubleBlack(grandparent, parent, db, path, i - 1);
            }
        }else {
            if(parent.right == db) {
                parent.left = y2;
                y.right = parent;
            }else {
                parent.right = y.left;
                y.left = parent;
            }
            parent.setRed();
            y.setBlack();
            connectNewParent(grandparent, parent, y);
            fixDoubleBlack(y, parent, db, path, i - 1);
        }
    }

    private void recolor(RBTreeNode<E> parent, RBTreeNode<E> newParent, RBTreeNode<E> c) {
        if(parent.isRed()){
            newParent.setRed();
        }else {
            newParent.setBlack();
        }
        parent.setBlack();
        c.setBlack();
    }

    private void connectNewParent(RBTreeNode<E> grandparent, RBTreeNode<E> parent, RBTreeNode<E> newParent) {
        if(parent == root){
            root = newParent;
            if(root != null) {
                newParent.setBlack();
            }
        }else if(grandparent.left == parent){
            grandparent.left = newParent;
        }else
            grandparent.right = newParent;
    }

    @Override
    protected void preorder(TreeNode<E> root) {
        if(root == null){
            return;
        }
        System.out.println(root.element + (((RBTreeNode<E>)root).isRed() ? "(red)" :"black"));
        preorder(root.left);
        preorder(root.right);
    }

    protected static class RBTreeNode<E extends Comparable<E>> extends BST.TreeNode<E>{
        private boolean red = true;

        public RBTreeNode(E e){
            super(e);
        }

        public boolean isRed(){
            return red;
        }

        public boolean isBlack(){
            return !red;
        }

        public void setBlack(){
            red = false;
        }

        public void setRed(){
            red = true;
        }
        int blackHeight;
    }
}