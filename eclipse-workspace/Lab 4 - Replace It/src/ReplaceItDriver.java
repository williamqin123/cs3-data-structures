
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ReplaceItDriver {

	public static void main(String[] args) throws IOException {
		
		Scanner scanner = new Scanner(new File("ReplaceIt.txt"));
		
		while(scanner.hasNextLine()) {
			String[] input = scanner.nextLine().split("\\s+");
			LinkedList<String> words = new LinkedList<String>(Arrays.asList(input));
			String replaceWith = words.getLast();
			words.removeLast();
			String wordToReplace = words.getLast();
			words.removeLast();
			
			ReplaceIt replacer = new ReplaceIt(words, wordToReplace, replaceWith);
			
			replacer.replace();
			
			System.out.println(replacer.toString());
		}
		scanner.close();
	}
}
