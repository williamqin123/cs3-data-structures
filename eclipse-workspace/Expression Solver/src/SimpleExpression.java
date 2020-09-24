
public class SimpleExpression {
	
	public static final int ADD = 0;
	public static final int SUBTRACT = 2;
	public static final int MULTIPLY = 3;
	public static final int DIVIDE = 4;
	
	private Object left, right;
	
	private int operation;

	// a number [ +, -, *, / ] another number
	public SimpleExpression(SimpleExpression input1, int op, SimpleExpression input2) {
		left = input1;
		right = input1;
		
	}
	
	// just a number
	public SimpleExpression(int value) {
		left = value;
		right = 0;
		operation = ADD;
	}
	
}
