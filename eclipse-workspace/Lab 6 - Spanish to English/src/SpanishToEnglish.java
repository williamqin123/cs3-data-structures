
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SpanishToEnglish {
	
	private Map<String, String> dictionary;

	public SpanishToEnglish() {
		dictionary = new HashMap<String, String>();
	}
	
	public void addTranslation(String spanishWord, String englishWord) {
		dictionary.put(spanishWord, englishWord);
	}
	
	public String getTranslation(String spanishWord) {
		return dictionary.get(spanishWord);
	}
	
	public String translatePhrase(String phrase) {
		String translated = "";
		for (String w : phrase.split("\\s")) {
			translated += getTranslation(w) + " ";
		}
		//return translated.substring(0, translated.length() - 1);
		return translated;
	}
}
