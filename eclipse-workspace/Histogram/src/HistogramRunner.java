
import java.io.*;
import java.util.*;

public class HistogramRunner {

	public static void main(String[] args) throws IOException {
		
		Scanner scanner = new Scanner(new File("histogram.txt"));
		
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			
			Histogram lineHistogram = new Histogram(line);
			
			System.out.println(lineHistogram.toString());
			
			System.out.println(); // space between histograms
		}
	}
}
