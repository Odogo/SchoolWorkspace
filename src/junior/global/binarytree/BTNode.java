package junior.global.binarytree;

public class BTNode<E> {

    private E data;
    private BTNode<E> left;
    private BTNode<E> right;

    public BTNode(E data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public BTNode(E data, BTNode<E> left, BTNode<E> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public E fetchData() { return data; }
    public BTNode<E> fetchLeft() { return left; }
    public BTNode<E> fetchRight() { return right; }

    public void setData(E data) { this.data = data; }
    public void setLeft(BTNode<E> node) { this.left = node; }
    public void setRight(BTNode<E> node) { this.right = node; }

    public boolean hasChildren() { return !(fetchLeft() != null && fetchRight() != null); }
}