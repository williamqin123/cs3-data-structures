
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class ExpressionSolver {
	
	List<String> tokens;
	
	public ExpressionSolver(String expression) {
		
		tokens = new ArrayList<String>(Arrays.asList(expression.split("\\s+")));
	}
	
	// does multiplication and division first, then does addition and subtraction in a second pass
	public int solve() {
		
		List<Integer> additionTerms = new ArrayList<Integer>();
		String operation = "*";
		int value = 1;
		
		int i = 0;
		for (String token : tokens) {
			if (token.equals("*") || token.equals("/")) {
				operation = token;
			}
			else if (token.equals("+") || token.equals("-")) {
				additionTerms.add(value);
				
				// converts subtraction to adding a negative number
				value = token.equals("+") ? 1 : (-1);
				
				operation = "*";
			}
			else {
				int num = Integer.parseInt(token);
				if (operation.equals("*")) value *= num;
				else if (operation.equals("/")) value /= num;
			}
			i++;
			if (i >= tokens.size()) {
				additionTerms.add(value);
			}
		}
		
		return (int) (additionTerms.stream().mapToDouble(a -> a).sum());
	}
}
