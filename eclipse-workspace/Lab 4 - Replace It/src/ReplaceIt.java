
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ReplaceIt {
	
	private List<String> words;
	private String toReplace;
	private String toReplaceWith;

	public ReplaceIt(List<String> wordList, String wordToReplace, String replaceWith) {
		words = new ArrayList<String>(wordList);
		toReplace = wordToReplace;
		toReplaceWith = replaceWith;
	}
	
	public void replace() {
		ListIterator<String> iter = words.listIterator();
		
		while (iter.hasNext()) {
			if (iter.next().equals(toReplace)) {
				iter.set(toReplaceWith);
			}
		}
	}
	
	@Override
	public String toString() {
		return words.toString();
	}
}
