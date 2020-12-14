import java.util.function.BiFunction;

public class JavaLinkedList {

    private LinkNode head;

    public JavaLinkedList(int[] list) {
        LinkNode n = head;
        for (int i : list) {
            LinkNode newNode = new LinkNode(i);
            if (n == null)
                head = newNode;
            else
                n.setNext(newNode);
            n = newNode;
        }
    }

    private double reduce(double initial, BiFunction<Double, Integer, Double> reducer) {
        LinkNode currentNode = head;
        double accumulator = initial;
        while (currentNode != null) {
            accumulator = reducer.apply(accumulator, currentNode.getValue());
            currentNode = currentNode.getNext();
        }
        return accumulator;
    }
    private double reduce(BiFunction<Double, Integer, Double> reducer) {
        return reduce(0, reducer);
    }

    private double getSum() {
        return reduce(Double::sum);
    }
    private int getLength() {
        return (int) reduce((Double accum, Integer currVal) -> accum + 1);
    }
    private double getAverage() {
        return getSum() / (double) getLength();
    }
    private int getSmallest() {
        return (int) reduce(Double.POSITIVE_INFINITY, Math::min);
    }
    private int getLargest() {
        return (int) reduce(Double.NEGATIVE_INFINITY, Math::max);
    }

    @Override
    public String toString() {
        return
            "SUM:: " + getSum() + "\n" +
            "AVERAGE:: " + getAverage() + "\n" +
            "SMALLEST:: " + getSmallest() + "\n" +
            "LARGEST:: " + getLargest() + "\n"
        ;
    }
}