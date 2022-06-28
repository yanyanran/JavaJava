class BST<E extends Comparable<E>> implements Tree<E>{
    protected TreeNode<E> root;
    protected int size = 0;

    public BST(){
    }

    public BST(E[] objects) {
        for(int i =0; i < objects.length; i++) {
            add(objects[i]);
        }
    }

    // 查找
    @Override
    public boolean search(E e){
        TreeNode current = root;

        while(current != null){  //不为空时->
            if(e.compareTo((E) current.element) < 0){  // 小于->
                current = current.left;  //指到left->
            }else if(e.compareTo((E) current.element) > 0){  // 大于->
                current = current.right;  //指到right->
            }else return true; // 相同即找到
        }
        return false;  // 没找到
    }

    /* 递归search
    public boolean search(E e) {
        return search(root, e);
    }

    public boolean search(TreeNode<E> root, E e) {
        if (root == null)
            return false;
        else if (e.compareTo(root.element) < 0)
            return search(root.left, e);
        else if (e.compareTo(root.element) > 0)
            return search(root.right, e);
         else
            return true;
    }
    * */

    // 插入
    @Override
    public boolean insert(E e){
        if(root == null){
            root = createNewNode(e); //如果树为空，创建一个新的根结点
        }else {
            // 否则寻找新元素父节点的位置
            TreeNode<E> parent = null;
            TreeNode<E> current = root;

            while(current != null){
                if(e.compareTo(current.element) < 0){
                    parent = current;
                    current = current.left;
                }else if(e.compareTo(current.element) > 0){
                    parent = current;
                    current = current.left;
                }else return false;
            }
            // 链接父结点
            if(e.compareTo(parent.element) < 0)
                parent.left = createNewNode(e);
            else
                parent.right = createNewNode(e);
        }
        size++;
        return true;
    }

    protected TreeNode<E> createNewNode(E e){
        return new TreeNode<>(e);
    }

    // 中序遍历
    @Override
    public void inorder(){
        inorder(root);
    }
    protected void inorder(TreeNode<E> root){
        if(root == null){  // 树为空时结束递归
            return;
        }
        inorder(root.left);  //递归左结点
        System.out.print(root.element + " ");  // 中间结点
        inorder(root.right);  // 递归右结点
    }

    // 后序遍历
    @Override
    public void postorder(){
        postorder(root);
    }
    protected void postorder(TreeNode<E> root){
        if(root == null){
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }

    // 前序遍历
    public void preorder(){
        preorder(root);
    }
    protected void preorder(TreeNode<E> root){
        if(root == null){
            return ;
        }
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }

    // 树结点class
    public static class TreeNode<E>{
        protected E element;
        protected TreeNode<E> left;
        protected TreeNode<E> right;

        public TreeNode(E e){
            element = e;
        }
    }

    @Override
    public int getSize(){
        return size;
    }

    public TreeNode<E> getRoot(){
        return root;
    }

    // 以数组线性表返回结点路径
    public java.util.ArrayList<TreeNode<E>> path(E e){
        java.util.ArrayList<TreeNode<E>> list = new java.util.ArrayList<>();
        TreeNode<E> current = root;

        while(current != null){
            list.add(current);
            if(e.compareTo(current.element) < 0){
                current = current.left;
            }else if(e.compareTo(current.element) > 0){
                current = current.right;
            }else break;
        }
        return list;
    }

    /**删除*/
    @Override
    public boolean delete(E e){
        TreeNode<E> parent = null;
        TreeNode<E> current = root;

        // 定位current和parent
        while(current != null){
            if(e.compareTo(current.element) < 0){
                parent = current;
                current = current.left;
            }else if(e.compareTo(current.element) > 0){
                parent = current;
                current = current.right;
            }else break;
        }

        if(current == null){
            return false;  // 结点不在树中
        }

        // 1、无左子结点
        if(current.left == null){
            if(parent == null){
                root = current.right;  // 没有父结点 就设为根结点
            }else {
                if(e.compareTo(parent.element) < 0){  // 删除current结点
                    parent.left = current.right;
                }else {
                    parent.right = current.right;
                }
            }
        }else{  // 2、有左子结点
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            // 定位rightMost和parentOfRightMost
            while (rightMost.right != null){
                parentOfRightMost = rightMost;
                rightMost = rightMost.right;
            }
            current.element = rightMost.element;  // 用rightMost替换current

            // 删除rightMost结点
            if(parentOfRightMost.right == rightMost){
                parentOfRightMost.right = rightMost.left;
            }else{
                parentOfRightMost.left = rightMost.left;
            }
        }
        size--;
        return true;
    }

    // 遍历迭代器
    @Override
    public java.util.Iterator<E> iterator() {
        return new InorderIterator();
    }
    private class InorderIterator implements java.util.Iterator<E>{
        private java.util.ArrayList<E> list = new java.util.ArrayList<>();
        private int current = 0;

        public InorderIterator(){
            inorder();
        }

        private void inorder(){
            inorder(root);
        }

        private void inorder(TreeNode<E> root){
            if(root == null){
                return;
            }
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        @Override
        public boolean hasNext(){
            if(current < list.size()){
                return true;
            }
            return false;
        }

        @Override
        public E next(){
            return list.get(current++);
        }

        @Override
        public void remove(){
            if(current == 0){
                throw new IllegalStateException();
            }
            delete(list.get(--current));
            list.clear();
            inorder();
        }
    }

    @Override
    public void clear(){
        root = null;
        size = 0;
    }
}