public class TestBST {
    public static void main(String[] args){
        BST<String> tree = new BST<>();
        tree.insert("GGG");
        tree.insert("EEE");
        tree.insert("AAA");
        tree.insert("OOO");
        tree.insert("PPP");
        tree.insert("LLL");

        System.out.print("Inorder (sorted) : ");
        tree.inorder();
        System.out.print("\nPostorder: ");
        tree.postorder();
        System.out.print("\nPreorder: ");
        tree.preorder();
        System.out.print("\nThe number of nodes is " + tree.getSize());

        System.out.print("\nIs MMM in the tree? ： " + tree.search("MMM"));

        System.out.print("\nA path from the root to MMM is: ");
        java.util.ArrayList<BST.TreeNode<String>> path = tree.path("MMM");
        for(int i = 0; path != null && i < path.size(); i++){
            System.out.print(path.get(i).element + " ");
        }

        Integer[] numbers = {2,46,5,7,85,22,1,6,3,9};
        BST<Integer> intTree = new BST<>(numbers);
        System.out.print("\nInorder (sorted) : ");
        intTree.inorder();
    }
}

/*
Inorder (sorted) : AAA LLL EEE GGG
Postorder: LLL AAA EEE GGG
Preorder: GGG EEE AAA LLL
The number of nodes is 6
Is MMM in the tree? ： false
A path from the root to MMM is: GGG
Inorder (sorted) : 1 9 2 22 
* */