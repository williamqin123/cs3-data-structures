import java.util.*;

public class PalinList {

    Queue<String> q = new LinkedList<>();

    public PalinList(String[] items) {
        q.addAll(Arrays.asList(items));
    }

    public boolean isPalin() {
        Stack<String> stack = new Stack<>();
        Queue<String> qCopy = new LinkedList<>(q);

        int uniqueSize = (int) Math.ceil(q.size() / 2.0);
        int repeatSize = q.size() / 2;

        int currentSize;
        while ((currentSize = qCopy.size()) != 0) {
            String item = qCopy.poll();

            if (currentSize <= repeatSize) {
                if (!item.equals(stack.peek())) {
                    return false;
                }
                stack.pop();
            }
            else if (currentSize > uniqueSize) {
                stack.push(item);
            }
        }

        return stack.isEmpty();
    }
}
