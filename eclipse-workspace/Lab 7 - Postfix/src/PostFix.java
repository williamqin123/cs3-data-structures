
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
import static java.lang.System.*;

public class PostFix {
	
	public static boolean isNumber(String s) {
		return Character.isDigit(s.charAt(0)) || (s.length() >= 2 && s.charAt(0) == '-' && Character.isDigit(s.charAt(1)));
	}
	
	 public static double evaluate(String ex) {
		
		double result = 0;
		 
        String[] tokens = ex.split(" ");
        
        Stack<Double> stack = new Stack<Double>();

        for (String token : tokens)
        {
            if (isNumber(token)) {
                stack.push(Double.parseDouble(token));
            }
            else {
                double operand2 = stack.pop().doubleValue();
                double operand1 = stack.pop().doubleValue();
                
                switch (token.charAt(0)) {
	    			case '+':
	    				result = operand1 + operand2;
	    				break;
	    			case '-':
	    				result = operand1 - operand2;
	    				break;
	    			case '*':
	    				result = operand1 * operand2;
	    				break;
	    			case '/':
	    				result = operand1 / operand2;
	    				break;
	    		}
                stack.push(result);
            }
        }
        return result;
	}

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner sc = new Scanner(new File("PostFix.txt"));
		
		while(sc.hasNextLine()) {
			
			String line = sc.nextLine();
			out.println(line + " = " + evaluate(line + " "));
		}
		sc.close();
	}
}
