
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Part implements Comparable {

	String partString;
	
	public Part(String data) {
		List<String> tmp = new ArrayList<String>(Arrays.asList(data.split("(?<=[0-9]) (?=[A-Za-z])")));
		Collections.reverse(tmp);
		partString = String.join(" ", tmp);
	}
	
	@Override
	public String toString() {
		return partString;
	}

	@Override
	public int compareTo(Object o) {
		return partString.compareTo(o.toString());
	}
}
