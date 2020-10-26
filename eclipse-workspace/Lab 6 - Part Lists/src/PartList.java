
import java.util.Map;
import java.util.TreeMap;

public class PartList {
	
	private Map<Part, Integer> inventory = new TreeMap<Part, Integer>();
	
	public PartList() {
		
	}
	
	public void addPart(Part part) {
		
	}
	
	public void addPart(String partStr) {
		addPart(new Part(partStr));
	}
}
