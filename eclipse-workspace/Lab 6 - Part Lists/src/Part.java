
import java.util.Arrays;
import java.util.Collections;

public class Part {

	String partString;
	
	public Part(String data) {
		partString = " ".join(Collections.reverse(Arrays.asList(data.split("[0-9] \\w"))).toArray());
	}
	
	@Override
	public String toString() {
		return partString;
	}
}
