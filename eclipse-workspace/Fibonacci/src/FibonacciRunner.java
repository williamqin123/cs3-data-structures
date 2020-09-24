
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FibonacciRunner {

	public static void main(String[] args) throws IOException {
		
		Scanner scanner = new Scanner(new File("fibonacci.txt"));
		
		while(scanner.hasNextLine()) {
			
			String line = scanner.nextLine();
			
			String[] input = line.split("\\s+");
			
			Fibonacci fibonacci = new Fibonacci(Integer.parseInt(input[0]));
			
			//System.out.println(fibonacci.toString());
			
			for (int i = 1; i < input.length; i++) {
			
				System.out.println(fibonacci.getTerm(Integer.parseInt(input[i])));
			
			}
			
			System.out.println("XXXXXX"); // separator
		}
		
		scanner.close();
	}
}
