
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class UniquesAndDupes {
	
	private static List<String> toList(String s) {
		
		return Arrays.asList(s.split("\\s+"));
	}
	
	public static <T> Set<T> union(Set<T> a, Set<T> b) {
	    Set<T> c = new TreeSet<T>(a);
	    c.addAll(b);
	    return c;
	}
	
	public static <T> Set<T> intersection(Set<T> a, Set<T> b) {
	    Set<T> c = new TreeSet<T>(a);
	    c.retainAll(b);
	    return c;
	}
	
	public static <T> Set<T> difference(Set<T> a, Set<T> b) {
	    Set<T> c = new TreeSet<T>(a);
	    c.removeAll(b);
	    return c;
	}
	
	public static <T> Set<T> symDiff(Set<T> a, Set<T> b) {
	    Set<T> c = union(a, b);
	    c.removeAll(intersection(a, b));
	    return c;
	}
	
	private static <T> Set<T> findDuplicates(List<T> list) {

	    Set<T> duplicates = new TreeSet<>();
	    Set<T> uniques = new TreeSet<>();

	    for(T t : list) {
	        if(!uniques.add(t)) {
	            duplicates.add(t);
	        }
	    }

	    return duplicates;
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter a set");
		
		List<String> list = toList(sc.nextLine());
		Set<String> set = new TreeSet<String>(list);
	    
	    System.out.println("Original List : " + String.join(" ", list) + " "); // bruh moment: Canvas requires a space at the end
	    
	    Set<String> dupes = findDuplicates(list);
	    
	    //System.out.println("Uniques : " + difference(set, dupes));
	    System.out.println("Uniques : " + set);
	    System.out.println("Dupes : " + dupes);
	    
		sc.close();
	}
}
