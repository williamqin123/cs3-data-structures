
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
import static java.lang.System.*;

public class SyntaxChecker {
	
	public static boolean isSyntaxValid(String ex) {
		
		Stack<Character> openGroups = new Stack<Character>();
		
		for (char c : ex.toCharArray()) {
			
			switch (c) {
				case '<':
					openGroups.push('>');
					break;
				case '(':
					openGroups.push(')');
					break;
				case '[':
					openGroups.push(']');
					break;
				case '{':
					openGroups.push('}');
					break;
					
				case '>':
				case ')':
				case ']':
				case '}':
					if (openGroups.size() > 0 && openGroups.peek() == c) openGroups.pop();
					else return false;
					break;
			}
		}
		return openGroups.size() == 0;
	}

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner sc = new Scanner(new File("SyntaxChecker.txt"));
		
		while(sc.hasNextLine()) {
			
			String line = sc.nextLine();
			out.println(line + " is " + (isSyntaxValid(line) ? "correct" : "incorrect") + ".\n");
		}
		sc.close();
	}
}
