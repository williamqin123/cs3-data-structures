public class LinkNode {

    private int val;
    private LinkNode next;

    public int getValue() {
        return val;
    }
    public void setValue(int newVal) {
        val = newVal;
    }

    public LinkNode getNext() {
        return next;
    }
    public void setNext(LinkNode newNext) {
        next = newNext;
    }

    public LinkNode(int val) {
        this.val = val;
    }
}