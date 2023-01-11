package junior.global.binarytree;

public class BinaryTree<E extends Comparable> {

    private BTNode<E> root;

    public BinaryTree(BTNode<E> root) {
        this.root = root;
    }

    public BinaryTree(E root) {
        this.root = new BTNode<E>(root);
    }

    public void add(E... items) {
        for(E item : items) add(item);
    }

    public void add(E item) {
        BTNode<E> temp = root;
        BTNode<E> prev = null;
        while(temp!=null) {
            int compare = temp.fetchData().compareTo(item);
            prev = temp;

            if(compare < 0) temp = temp.fetchLeft();
            else temp = temp.fetchRight();
        }

        // prev is the parent of the insertion point.
        if(prev == null) {
            // empty tree
            root = new BTNode<E>(item);
        }else {
            int compare = prev.fetchData().compareTo(item);
            if(compare < 0) prev.setLeft(new BTNode<E>(item));
            else prev.setRight(new BTNode<E>(item));
        }
    }

    public void traverseTreeInfix() {
        _traverseTreeInfix(root);
    }

    private void _traverseTreeInfix(BTNode node) {
        if(node == null) return;

        _traverseTreeInfix(node.fetchLeft());
        System.out.println(node.fetchData());
        _traverseTreeInfix(node.fetchRight());
    }
}