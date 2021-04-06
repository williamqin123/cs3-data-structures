import java.util.*;

@SuppressWarnings("unchecked")

public class BinarySearchTree<T extends Comparable<T>> {

    private BinaryNode<T> root;

    public BinarySearchTree() {

    }

    public BinaryNode<T> getRoot() {
        return root;
    }

    public void add(BinaryNode<T> node) {
        if (root == null)
            root = node;
        else
            add(root, node.getVal());
    }

    public void add(T val) {
        if (root == null)
            root = new BinaryNode<T>(val);
        else
            add(root, val);
    }

    private void add(BinaryNode<T> cur, T val) {
        BinaryNode<T> next = (val.compareTo(cur.getVal()) < 0) ? cur.left() : cur.right();
        if (next == null) {
            boolean isLeft = val.compareTo(cur.getVal()) < 0;
            BinaryNode<T> node = new BinaryNode<T>(val, cur, isLeft);
            node.setParent(cur);
            if (val.compareTo(cur.getVal()) < 0)
                cur.left(node);
            else
                cur.right(node);
        } else {
            add(next, val);
        }
    }

    public boolean contains(T val) {
        return search(root, val) != null;
    }

    public BinaryNode<T> remove(T val) {
        return remove(val, root);
    }

    public BinaryNode<T> remove(T val, BinaryNode<T> start) {

        BinaryNode<T> node = search(start, val);
        if (node == null)
            return null;
        BinaryNode<T> par = node.getParent();
        if (node.left() == null && node.right() == null) {
            if (node.equals(root)) {
                root = null;
            } else {
                if (node.isLeftChild())
                    par.left(null);
                else
                    par.right(null);
            }
        } else if (node.left() == null || node.right() == null) {
            BinaryNode<T> child = (node.left() != null) ? node.left() : node.right();
            if (node.equals(root)) {
                root = child;
            } else {
                if (node.isLeftChild()) {
                    par.left(child);
                    child.setIsLeft(true);
                } else {
                    par.right(child);
                    child.setIsLeft(false);
                }
                child.setParent(par);
            }
        } else {
            if(node==root)root = node.left();
            else {
                if(node.isLeftChild())node.getParent().left(node.left());
                else{
                    node.left().setIsLeft(false);
                    node.getParent().right(node.left());
                }
            }
            BinaryNode<T> temp = node.left();
            while(temp.right()!=null)temp=temp.right();
            temp.right(node.right());
            node.right().setParent(temp);
            node.left().setParent(node.getParent());
        }
        node.left(null);
        node.right(null);
        return node;
    }

    public BinaryNode<T> search(BinaryNode<T> cur, T val) {
        if (cur == null)
            return null;
        if (cur.getVal().compareTo(val) == 0)
            return cur;
        BinaryNode<T> next = (val.compareTo(cur.getVal()) < 0) ? cur.left() : cur.right();
        return search(next, val);
    }

    private void swap(BinaryNode<T> x, BinaryNode<T> y){
        T k = x.getVal();
        x.setVal(y.getVal());
        y.setVal(k);
    }

    public int getHeight() {
        return getHeight(root) - 1;
    }

    private int getHeight(BinaryNode<T> cur) {
        if (cur == null)
            return 0;
        return 1 + Math.max(getHeight(cur.left()), getHeight(cur.right()));
    }

    public int getWidth() {
        return getHeight(root.left())+getHeight(root.right())+2;
    }

    public String preOrder() {
        StringBuilder s = new StringBuilder("");
        preOrder(root, s);
        return s.toString();
    }

    private void preOrder(BinaryNode<T> cur, StringBuilder s) {
        if (cur == null)
            return;
        s.append(cur.getVal()).append(" ");
        preOrder(cur.left(), s);
        preOrder(cur.right(), s);
    }

    public String inOrder() {
        StringBuilder s = new StringBuilder("");
        inOrder(root, s);
        return s.toString();
    }

    private void inOrder(BinaryNode<T> cur, StringBuilder s) {
        if (cur == null)
            return;
        inOrder(cur.left(), s);
        s.append(cur.getVal()).append(" ");
        inOrder(cur.right(), s);
    }

    public String postOrder() {
        StringBuilder s = new StringBuilder("");
        postOrder(root, s);
        return s.toString();
    }

    private void postOrder(BinaryNode<T> cur, StringBuilder s) {
        if (cur == null)
            return;
        postOrder(cur.left(), s);
        postOrder(cur.right(), s);
        s.append(cur.getVal()).append(" ");
    }

    public String reverseOrder() {
        StringBuilder s = new StringBuilder("");
        reverseOrder(root, s);
        return s.toString();
    }

    private void reverseOrder(BinaryNode<T> cur, StringBuilder s) {
        if (cur == null)
            return;
        reverseOrder(cur.right(), s);
        s.append(cur.getVal()).append(" ");
        reverseOrder(cur.left(), s);
    }

    public String levelOrder() {
        ArrayList<String> list = new ArrayList<>();
        ArrayDeque<BinaryNode<T>> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryNode<T> cur = queue.poll();
            list.add(cur.getVal().toString());
            if (cur.left() != null) {
                queue.add(cur.left());
            }
            if (cur.right() != null) {
                queue.add(cur.right());
            }
        }
        return list.toString();
    }

    public ArrayList<String> fullLevelOrder() {
        LinkedList<BinaryNode<T>> queue = new LinkedList<>();
        int height = Math.min(getHeight()+1, 6);
        ArrayList<String> list = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryNode<T> cur = queue.poll();
            if (cur == null) {
                queue.add(null);
                queue.add(null);
                list.add(null);
            } else {
                queue.add(cur.left());
                queue.add(cur.right());
                list.add(cur.getVal().toString());
            }
            if ((int) (Math.log10(queue.size()) / Math.log10(2)) == height + 1) {
                break;
            }
        }
        return list;
    }

    public void printFullTree(ArrayList<String> list, int levels) {
        int num = 1;
        int idx = 0;
        String s = "";
        for (int i = 0; i < levels; i++) {
            for (int j = 0; j < num; j++) {
                s += list.get(idx) + "|";
                idx++;
            }
            num *= 2;
            s += "\n";
        }
        System.out.println(s);
    }

    public int getNumNodes() {
        return countNodes(root, false);
    }

    public int getNumLeaves() {
        return countNodes(root, true);
    }

    public int getNumLevels() {
        return getHeight() + 1;
    }

    private int countNodes(BinaryNode<T> current, boolean leavesOnly) {
        if (current == null)
            return 0;
        int add;
        if (leavesOnly)
            add = (current.left() == null && current.right() == null) ? 1 : 0;
        else
            add = 1;
        return add + countNodes(current.left(), leavesOnly) + countNodes(current.right(), leavesOnly);
    }

    public boolean isFull() {
        return (int) (Math.log10(getNumNodes()) / Math.log10(2)) == getHeight();
    }

    public T getLargest() {
        return findMax(root);
    }

    public T getSmallest() {
        return findMin(root);
    }

    private T findMax(BinaryNode<T> cur) {
        if (cur == null)
            return (T) ("" + (char) ('A' - 1));
        T left = findMax(cur.left());
        T right = findMax(cur.right());
        T max = (left.compareTo(right) > 0) ? left : right;
        return (max.compareTo(cur.getVal()) > 0) ? max : cur.getVal();
    }

    private T findMin(BinaryNode<T> cur) {
        if (cur == null)
            return (T) ("" + (char) ('z' + 1));
        T left = findMin(cur.left());
        T right = findMin(cur.right());
        T min = (left.compareTo(right) < 0) ? left : right;
        return (min.compareTo(cur.getVal()) < 0) ? min : cur.getVal();
    }

    public String toString() {
        return inOrder();
    }
}