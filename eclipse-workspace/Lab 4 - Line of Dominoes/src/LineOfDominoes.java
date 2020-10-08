
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class LineOfDominoes {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		
		
		System.out.println("Enter in maximum pip size:");
		int maxPips = sc.nextInt();
		sc.nextLine();
		
		System.out.println("Enter in a random seed:");
		int randSeed = sc.nextInt();
		sc.nextLine();
		
		System.out.println("Starting set");
		DominoSet dominoes = new DominoSet(maxPips);
		dominoes.shuffle(randSeed);
		System.out.println(dominoes);
		
		DominoSet removedDominoes = new DominoSet(removeUnmatchedDominoes(dominoes));
		
		sc.close();
	}

	private static List<Domino> removeUnmatchedDominoes(DominoSet dominoes) {
		
		List<Domino> removed = new ArrayList<Domino>();
		
		ListIterator<Domino> iter = dominoes.getList().listIterator();
		while (iter.hasNext()) {
			dominoSetString += iter.next().toString();
		}
		
		return removed;
	}
}
