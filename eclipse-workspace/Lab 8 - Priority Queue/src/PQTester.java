import java.util.Arrays;
import java.util.PriorityQueue;

public class PQTester extends PriorityQueue<String> {

    public PQTester(String[] items) {
        addAll(Arrays.asList(items));
    }

    public String getMin() {
        return peek();
    }

    public String getNaturalOrder() {
        String orderedList = "";
        PriorityQueue<String> pqCopy = new PriorityQueue<String>(this);

        while (pqCopy.size() != 0) {
            orderedList += pqCopy.poll() + " ";
        }

        return orderedList;
    }
}
