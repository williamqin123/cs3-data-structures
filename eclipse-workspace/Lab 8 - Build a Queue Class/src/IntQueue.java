import java.util.ArrayList;

public class IntQueue {

    ArrayList<Integer> q = new ArrayList<>();

    public boolean isEmpty() {
        return q.size() == 0;
    }

    public void add(int e) {
        q.add(e);
    }

    public int peek() {
        return q.get(0);
    }

    public int remove() {
        Integer e = peek();
        q.remove(e);
        return e;
    }

    @Override
    public String toString() {
        return q.toString();
    }
}