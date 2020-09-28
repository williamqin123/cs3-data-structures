
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ExpressionSolverRunner {
	
	public static void main(String[] args) throws IOException {
	
		Scanner scanner = new Scanner(new File("ExpressionSolver.txt"));
		
		while(scanner.hasNextLine()) {
			String expression = scanner.nextLine();
			ExpressionSolver solver = new ExpressionSolver(expression);
			
			System.out.println(expression + " = " + solver.solve());
		}
		
		scanner.close();
	}
}
