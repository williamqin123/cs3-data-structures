
import java.util.Arrays;

public class Fibonacci {
	
	int[] sequence;

	public Fibonacci(int size) {
		
		if (size < 1) return;
		
		sequence = new int[size];
		
		sequence[0] = 1;
		
		if (size < 2) return;
		
		int x1 = 0, x2 = 1;
		
		for (int i = 1; i < size; i++) {
			
			sequence[i] = x1 + x2;
			
			x1 = x2;
			x2 = sequence[i];
		}
	}
	
	public int getTerm(int index) {
		return (index >= 1 && index <= sequence.length) ? sequence[index - 1] : -1;
	}
	
	public String toString() {
		return Arrays.toString(sequence);
	}
}
