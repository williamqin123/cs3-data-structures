import static java.lang.System.*;

public class ListFunHouse
{
    //this method will print the entire list on the screen
    public static void print(ListNode list)
    {
        ListNode currentNode = list;
        while (currentNode != null) {
            out.print(currentNode.getValue() + " ");
            currentNode = currentNode.getNext();
        }
        out.println();
    }

    //this method will return the number of nodes present in list
    public static int nodeCount(ListNode list)
    {
        ListNode currentNode = list;
        int count = 0;
        while (currentNode != null) {
            count++;
            currentNode = currentNode.getNext();
        }
        return count;
    }

    //this method will create a new node with the same value as the first node and add this
    //new node to the list.  Once finished, the first node will occur twice.
    public static void doubleFirst(ListNode list)
    {
        list.setNext(list.clone());
    }

    //this method will create a new node with the same value as the last node and add this
    //new node at the end.  Once finished, the last node will occur twice.
    public static void doubleLast(ListNode list)
    {
        ListNode lastNode = list;
        while (lastNode != null && lastNode.getNext() != null) {
            lastNode = lastNode.getNext();
        }
        lastNode.setNext(lastNode.clone());
    }

    //method skipEveryOther will remove every other node
    public static void skipEveryOther(ListNode list)
    {
        removeXthNode(list, 2);
    }

    //this method will set the value of every xth node in the list
    public static void setXthNode(ListNode list, int x, Comparable value)
    {
        ListNode currentNode = list;
        int counter = 0;
        while (currentNode != null) {
            if (currentNode.getNext() != null && counter % x == x - 1)
                currentNode.setValue(value);
            counter = (counter + 1) % x;
            currentNode = currentNode.getNext();
        }
    }

    //this method will remove every xth node in the list
    public static void removeXthNode(ListNode list, int x)
    {
        ListNode currentNode = list;
        int counter = 0;
        while (currentNode != null) {
            if (currentNode.getNext() != null && counter % x == Math.max(x - 2, 0)) {
                currentNode.setNext(currentNode.getNext().getNext());
                counter++;
            }
            counter++;
            currentNode = currentNode.getNext();
        }
    }

    //this method will add a node at the end of the list
    public static ListNode add(ListNode list, Comparable val)
    {
        ListNode newNode = new ListNode(val, null);
        if (list == null) {
            list = newNode;
            return list;
        }
        ListNode lastNode = list;
        while (lastNode != null && lastNode.getNext() != null) {
            lastNode = lastNode.getNext();
        }
        lastNode.setNext(newNode);
        return list;
    }
}