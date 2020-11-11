import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PreFix {
	
	public static String pop(String s, boolean skip) {
		String s2 = s;
		int go = 1;
		while (go > 0) {
			s2 = s2.substring(s.indexOf(" ") + 1);
			go--;
			if (skip && (s2.charAt(0) == '+' || s2.charAt(0) == '-' || s2.charAt(0) == '*' || s2.charAt(0) == '/'))
				go = 2;
		}
		return s2;
	}
	
	public static double opSwitch(String s) {
		char token = s.charAt(0);
		
		if (token == '+') {
			return add(pop(s, false));
		}
		else if (token == '-') {
			return subtract(pop(s, false));
		}
		else if (token == '*') {
			return multiply(pop(s, false));
		}
		else if (token == '/') {
			return divide(pop(s, false));
		}
		return Double.parseDouble(String.valueOf(token));
	}
	
	public static double add(String s) {
		return opSwitch(s) + opSwitch(pop(s, true));
	}
	public static double subtract(String s) {
		return opSwitch(s) - opSwitch(pop(s, true));
	}
	public static double multiply(String s) {
		return opSwitch(s) * opSwitch(pop(s, true));
	}
	public static double divide(String s) {
		return opSwitch(s) / opSwitch(pop(s, true));
	}

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner sc = new Scanner(new File("PreFix.txt"));
		
		while(sc.hasNextLine()) {
			
			String line = sc.nextLine();
			System.out.println(line + " = " + opSwitch(line));
		}
		sc.close();
	}
}
