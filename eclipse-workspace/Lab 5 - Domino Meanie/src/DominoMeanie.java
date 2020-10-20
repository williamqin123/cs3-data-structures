
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class DominoMeanie {

    public static void main(String args[]) {
    	
    	Scanner input = new Scanner(System.in);
		
    	
    	
		System.out.println("Enter in the number of players:");
		int playerCount = input.nextInt();

    	System.out.println("Enter in the max pip size:");
		int maxPips = input.nextInt();
    	
    	// provided code
		System.out.println("Enter in the random seed value:");
		int randSeed = input.nextInt();
		Random randNum = new Random(randSeed);
		
		
		
		DominoSet allDominoes = new DominoSet(maxPips);
		ListIterator iter = allDominoes.getList().listIterator();
		List toGivePlayer = new ArrayList<Domino>();
		
		int div = 0;
		IntStream.range(0, playerCount).forEachOrdered(p -> {
			
			if ()
			
			div++;
		});
		
		
		
		input.close();
	}
}
