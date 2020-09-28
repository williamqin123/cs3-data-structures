
public class Histogram {
	
	int[] histogram;
	
	public Histogram(String digitStr) {
		histogram = new int[10];
		fillArray(digitStr);
	}
	
	public void fillArray(String digitStr) {
		for (char c : digitStr.toCharArray()) {
			histogram[Integer.parseInt(String.valueOf(c))]++;
		}
	}

	@Override
	public String toString() {
		String output = "";
		
		for (int digit = 0; digit < 10; digit++) {
			output += digit + " - " + histogram[digit] + "\n";
		}
		
		return output.substring(0, output.length() - 1); // cuts off the ending newline
	}
}
