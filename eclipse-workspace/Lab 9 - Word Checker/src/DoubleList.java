import static java.lang.System.*;

public class DoubleList
{
    private DoubleNode front,back;
    private int size;

    public DoubleList( )
    {
        front = back = null;
        size = 0;
    }

    //ADDS NEW NODE TO THE BACK OF THE LIST FOR WORD IF IT DOES NOT EXIST.
    //IF IT EXISTS, IT BUMPS UP WORD'S COUNT BY ONE
    public void add(String word)
    {
        DoubleNode newNode = new DoubleNode(word, 1, null, null);

        DoubleNode currentNode = front;

        if (currentNode == null) {
            newNode.setPrev(newNode);
            newNode.setNext(newNode);
            front = back = newNode;
        }

        else {

            while (currentNode != back && !currentNode.getWord().equals(word)) {
                currentNode = currentNode.getNext();
            }

            if (currentNode.getWord().equals(word)) {
                currentNode.setWordCount(currentNode.getWordCount() + 1);
            } else {
                newNode.setPrev(currentNode);
                currentNode.setNext(newNode);
                back = newNode;
                back.setNext(front);
                front.setPrev(back);
            }
        }

        size++;
    }

    //REMOVE ONE COUNT OF THE WORD FROM THE LIST, IF THE COUNT RESULTS IN ZERO
    //REMOVE THE WORD FROM LIST. RETURN THE NEW COUNT OF THE WORD OR ZERO
    //IF THE WORD DOES NOT EXIST IN LIST
    public int remove(String word)
    {
        DoubleNode currentNode = front;

        back.setNext(null);
        while (currentNode != null) {
            if (currentNode.getWord().equals(word)) {
                break;
            }
            currentNode = currentNode.getNext();
        }
        back.setNext(front);

        if (currentNode == null) return 0;

        int newWordCount = currentNode.getWordCount() - 1;
        currentNode.setWordCount(newWordCount);

        if (newWordCount == 0) {

            DoubleNode prev = currentNode.getPrev();
            DoubleNode next = currentNode.getNext();

            if (size() == 1) {
                front = back = null;
            } else if (prev == back) {
                front = next;
                front.setPrev(back);
            } else {
                prev.setNext(next);
                if (next == front) {
                    back = prev;
                } else {
                    next.setPrev(prev);
                }
            }
        }

        return newWordCount;
    }

    // WILL RETURN THE WORD AT X. THE FRONT IS CONSIDERED 0.
    // IF X IS NEGATIVE IT WILL COUNT USING PREVIOUS
    // IF X IS POSITIVE IT WILL COUNT USING NEXT
    public String getWord(int x)
    {
        assert front != null;
        assert (x < 0 ? Math.abs(x) - 1 : x) < size();
        String word = front.getWord();

        if (x != 0) {
            DoubleNode currentNode;
            int currentIndex = 0;

            if (x > 0) {
                currentNode = front;
                while (currentIndex < x) {
                    currentNode = currentNode.getNext();
                    currentIndex++;
                }
            }

            else {
                currentNode = back;
                currentIndex = -1;
                while (currentIndex > x) {
                    currentNode = currentNode.getPrev();
                    currentIndex--;
                }
            }

            return currentNode.getWord();
        }

        return word;
    }

    //OPTIONAL PRIVATE METHOD
    private void removeNode(DoubleNode x, DoubleNode y, DoubleNode z)
    {

    }

    //RETURNS THE SIZE OF THE LIST
    public int size()
    {
        return size;
    }

    //RETURNS IF THE LIST CONTAINS THE WORD IN IT
    public boolean contains(String word) {
        DoubleNode currentNode = front;

        back.setNext(null);
        while (currentNode != null) {
            if (currentNode.getWord().equals(word)) return true;
            currentNode = currentNode.getNext();
        }
        back.setNext(front);

        return false;
    }

    //RETURNS THE LIST AS A BIG STRING
    public String toString()
    {
        String str = "";

        DoubleNode currentNode = front;

        back.setNext(null);
        while (currentNode != null) {
            str += currentNode.getWord() + '-' + currentNode.getWordCount() + ' ';
            currentNode = currentNode.getNext();
        }
        back.setNext(front);

        return str;
    }
}