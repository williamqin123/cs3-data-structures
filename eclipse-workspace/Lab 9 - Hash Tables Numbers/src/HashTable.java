import java.util.LinkedList;
import static java.lang.System.*;

public class HashTable {

    LinkedList<Number>[] buckets = new LinkedList[10];

    public HashTable() {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    public void add(Number number) {
        LinkedList<Number> bucket = buckets[number.hashCode()];
        if (bucket.contains(number))
            return;
        bucket.add(number);
    }

    /*
    public Object get(Number key) {
        What're the keys and values in this table?
    }
    */

    @Override
    public String toString() {
        String tableDisplay = "";
        tableDisplay += "HASHTABLE\n";
        for (int i = 0; i < buckets.length; i++) {
            LinkedList<Number> bucketList = buckets[i];
            tableDisplay += "bucket " + i;
            for (Number n : bucketList) {
                tableDisplay += " " + n.getValue();
            }
            tableDisplay += '\n';
        }
        return tableDisplay;
    }
}
