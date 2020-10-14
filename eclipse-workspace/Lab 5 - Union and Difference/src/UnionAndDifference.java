
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class UnionAndDifference {
	
	private static Set<Integer> makeIntSetFromDSV(String s, String delimeter) {
		
		return new TreeSet<Integer>(Arrays.stream(s.split(delimeter)).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList()));
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

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter in first set:");
		Set<Integer> a = makeIntSetFromDSV(sc.nextLine(), "\\s+");
		
		System.out.println("Enter in second set:");
	    Set<Integer> b = makeIntSetFromDSV(sc.nextLine(), "\\s+");
	    
	    System.out.println("Set one " + a);
	    System.out.println("Set two " + b);
	    
	    System.out.println();
	    
	    System.out.println("UNION - " + union(a, b));
	    System.out.println("INTERSECTION - " + intersection(a, b));
	    System.out.println("difference A-B - " + difference(a, b));
	    System.out.println("difference B-A - " + difference(b, a));
	    System.out.println("symmetric difference - " + symDiff(b, a));
		
		sc.close();
	}
}
