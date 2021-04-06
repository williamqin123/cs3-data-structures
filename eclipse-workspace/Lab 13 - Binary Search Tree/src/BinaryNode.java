@SuppressWarnings("unchecked")

public class BinaryNode<T extends Comparable<T>> {

    private BinaryNode<T> right, left;
    private BinaryNode<T> parent;
    private boolean isLeftChild;
    private T val;

    public BinaryNode(T x) {
        val = x;
        right = null;
        left = null;
    }
    public BinaryNode(T x, BinaryNode<T> parent, boolean isLeft) {
        val = x;
        this.parent = parent;
        this.isLeftChild = isLeft;
    }

    public BinaryNode<T> right() {
        return right;
    }
    public void right(BinaryNode<T> right) {
        this.right = right;
    }

    public BinaryNode<T> left() {
        return left;
    }
    public void left(BinaryNode<T> leftChild) {
        this.left = leftChild;
    }

    public boolean isLeftChild() {
        return isLeftChild;
    }
    public void setIsLeft(boolean isLeft) {
        this.isLeftChild = isLeft;
    }

    public T getVal() {
        return val;
    }
    public void setVal(T val) {
        this.val = val;
    }

    public BinaryNode<T> getParent() {
        return parent;
    }
    public void setParent(BinaryNode<T> parent) {
        this.parent = parent;
    }

    public String toString() {
        return "Value:" + getVal() + ", Left:" + (left == null ? null : left.getVal()) + ", Right:" + (right == null ? null : right.getVal());
    }
}