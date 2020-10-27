
import java.util.Map;
import java.util.TreeMap;

public class PartList {
	
	private Map<Part, Integer> inventory;
	
	public PartList() {
		inventory = new TreeMap<Part, Integer>();
	}
	
	public void addPart(Part part) {
		inventory.putIfAbsent(part, 0);
		inventory.put(part, inventory.get(part) + 1);
	}
	
	public void addPart(String partStr) {
		addPart(new Part(partStr));
	}
	
	public String orderSheet(int size) {
		String returnVal = "";
		for (Part p : inventory.keySet()) {
			returnVal += size - inventory.get(p) + " " + p + "\n";
		}
		return returnVal;
	}
	
	@Override
	public String toString() {
		String returnVal = "";
		for (Part p : inventory.keySet()) {
			returnVal += p + " - " + inventory.get(p) + "\n";
		}
		return returnVal;
	}
}
