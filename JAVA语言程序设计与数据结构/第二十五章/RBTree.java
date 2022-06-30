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
                restructureRecolor(u, v, w, w, parentOfw);

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