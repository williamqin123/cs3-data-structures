
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class PreFix {
	
	public static String pop(String s) {
		return s.substring(s.indexOf(" ") + 1);
	}
	
	public static boolean isNumber(String s) {
		return Character.isDigit(s.charAt(0)) || (s.charAt(0) == '-' && Character.isDigit(s.charAt(1)));
	}
	
	public static double evaluate(String ex) {
		
		if (isNumber(ex)) return Double.parseDouble(ex.substring(0, ex.indexOf(' ')));
		
		String ex2 = pop(ex);	
		double operand1 = evaluate(ex2);
		double operand2;
		if (isNumber(ex2)) operand2 = evaluate(pop(ex2));
		else {
			int stackDepth = 1;
			Stack<Integer> count = new Stack<Integer>();
			count.add(0);
			
			ex2 = pop(ex2);
			while (stackDepth > 0) {
				count.set(count.size() - 1, count.peek() + 1);
				if (!isNumber(ex2)) {
					stackDepth++;
					count.add(0);
				}
				ex2 = pop(ex2);
				while (count.size() > 0 && count.peek() >= 2) {
					stackDepth--;
					count.pop();
				}
			}
			operand2 = evaluate(ex2);
		}
		
		switch (ex.charAt(0)) {
			case '+':
				return operand1 + operand2;
			case '-':
				return operand1 - operand2;
			case '*':
				return operand1 * operand2;
			case '/':
				return operand1 / operand2;
			default:
				return 0;
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner sc = new Scanner(new File("PreFix.txt"));
		
		while(sc.hasNextLine()) {
			
			String line = sc.nextLine();
			System.out.println(line + " = " + evaluate(line + " "));
		}
		sc.close();
	}
}
