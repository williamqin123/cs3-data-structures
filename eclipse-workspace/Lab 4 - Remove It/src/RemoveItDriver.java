
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class RemoveItDriver {

	public static void main(String[] args) throws IOException {
		
		Scanner scanner = new Scanner(new File("RemoveIt.txt"));
		
		while(scanner.hasNextLine()) {
			String[] input = scanner.nextLine().split("\\s+");
			LinkedList<String> words = new LinkedList<String>(Arrays.asList(input));
			String wordToRemove = words.getLast();
			words.removeLast();
			
			RemoveIt remover = new RemoveIt(words, wordToRemove);
			
			remover.remove();
			
			System.out.println(remover.toString());
		}
		scanner.close();
	}
}
