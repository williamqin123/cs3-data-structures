public class BinaryCountNode {

    Comparable c;
    int dupeCount = 0;

    public BinaryCountNode(Comparable c) {
        this.c = c;
    }

    public void incrementDupeCount() {
        ++dupeCount;
    }
    public boolean decrementDupeCount() { // returns whether the node should be removed
        return --dupeCount <= 0;
    }
}
