
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Histogram {
	
	Map<String, Integer> map;

	public Histogram(String chars) {
		map = new TreeMap<String, Integer>();
		
		String[] charArray = chars.split("\\s");
		
        for (String c : charArray) { 
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1); 
            } 
            else {
                map.put(c, 1); 
            }
        } 
	}
	
	@Override
	public String toString() {
        String returnVal = "char\t1---5----01---5\n";
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
        	String dots = "";
        	for (int i = 0; i < entry.getValue(); i++) {
        		dots += "*";
        	}
            returnVal += entry.getKey() + "\t" + dots + "\n";
        }
        return returnVal;
	}	
}
