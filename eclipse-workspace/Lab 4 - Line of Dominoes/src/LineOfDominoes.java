
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class LineOfDominoes {
	
	public static boolean isJUnitTest() {
	    StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
	    List<StackTraceElement> list = Arrays.asList(stackTrace);
	    for (StackTraceElement element : list) {
	        if (element.getClassName().startsWith("org.junit.")) {
	            return true;
	        }           
	    }
	    return false;
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// No sc.nextLine() in Canvas submissions after sc.nextInt()
		// Canvas unit tests have the full input streams ready to read from; it doesn't simulate keyboard input (entering things one at a time)
		// Clearing the scanner buffer with sc.nextLine() throws NoSuchElementException on Canvas
		
		System.out.println("Enter in maximum pip size:");
		int maxPips = sc.nextInt();
		//sc.nextLine();
		
		System.out.println("Enter in a random seed:");
		int randSeed = sc.nextInt();
		//sc.nextLine();
		
		System.out.println("Starting set");
		DominoSet dominoes = new DominoSet(maxPips);
		dominoes.shuffle(randSeed);
		System.out.println(dominoes);
		
		DominoSet removedDominoes = new DominoSet(removeUnmatchedDominoes(dominoes));
		
		autoChain(dominoes, removedDominoes);
		
		System.out.println("Final set");
		System.out.println(dominoes);
		System.out.println("Number of dominoes left: " + removedDominoes.size());
		
		
		
		sc.close();
	}

	private static void autoChain(DominoSet targetSet, DominoSet sourceSet) {
		
		ArrayList<Domino> chain = (ArrayList<Domino>) targetSet.getList();
		
		while (true) {
			Domino tail = chain.get(chain.size() - 1);
			
			ListIterator<Domino> iter = sourceSet.getList().listIterator();
			
			boolean foundDominoToAddToChain = false;
			
			while (iter.hasNext()) {
				foundDominoToAddToChain = false;
				
				Domino potentiallyAddableDomino = iter.next();
				
				if (potentiallyAddableDomino.getRightPips() == tail.getRightPips()) {
					potentiallyAddableDomino.rotate();
				}
				if (potentiallyAddableDomino.getLeftPips() == tail.getRightPips()) {
					iter.remove();
					chain.add(potentiallyAddableDomino);
					foundDominoToAddToChain = true;
					break;
				}
			}
			if (!foundDominoToAddToChain) break;
		}
	}

	private static List<Domino> removeUnmatchedDominoes(DominoSet dominoes) {
		
		List<Domino> removed = new ArrayList<Domino>();
		
		if (dominoes.size() == 0) return removed;
		
		ListIterator<Domino> iter = dominoes.getList().listIterator();
		
		Domino prevDomino = iter.next();
		
		while (iter.hasNext()) {
			Domino currentDomino = iter.next();
			if (currentDomino.getLeftPips() != prevDomino.getRightPips()) {
				currentDomino.rotate();
			}
			if (currentDomino.getLeftPips() != prevDomino.getRightPips()) {
				iter.remove();
				removed.add(currentDomino);
			}
			else {
				prevDomino = currentDomino;
			}
		}
		return removed;
	}
}
