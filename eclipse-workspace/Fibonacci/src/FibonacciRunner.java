
import java.io.File;
import java.util.Scanner;

public class FibonacciRunner {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(new File("histogram.txt"));
		
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			
			Histogram lineHistogram = new Histogram(line);
			
			System.out.println(lineHistogram.toString());
			
			System.out.println(); // space between histograms
		}

	}

}
