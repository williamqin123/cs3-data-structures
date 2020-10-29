
import static java.lang.System.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpanRunner {

	public static void main(String[] args) {
		
		SpanishToEnglish translator = new SpanishToEnglish();
		Scanner input = new Scanner(in);
		
		out.println("Enter in the number of words in dictionary");
		int size = input.nextInt();
		input.nextLine();
		
		out.println("Enter in the spanish word and english word");
		
		while(size>0) {
			size--;
			String[] words = input.nextLine().split("\\s");
			translator.addTranslation(words[0], words[1]);
		}
		
		out.println("Enter in the number of phrases to translate");
		size = input.nextInt();
		input.nextLine();
		
		out.println("Enter in the line to translate");
		
		List<String> translatedLines = new ArrayList<String>();
		while(size>0) {
			size--;
			translatedLines.add(translator.translatePhrase(input.nextLine()));
		}
		
		out.println(String.join("\n", translatedLines));
		
		input.close();
	}
}
