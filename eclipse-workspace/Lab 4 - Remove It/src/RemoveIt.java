
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class RemoveIt {
	
	private List<String> words;
	private String unwantedWord;

	public RemoveIt(List<String> wordList, String wordToRemove) {
		words = new ArrayList<String>(wordList);
		unwantedWord = wordToRemove;
	}
	
	public void remove() {
		ListIterator<String> iter = words.listIterator();
		
		while (iter.hasNext()) {
			if (iter.next().equals(unwantedWord)) {
				iter.remove();
			}
		}
	}
	
	@Override
	public String toString() {
		return words.toString();
	}
}
