
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.IntStream;

public class DominoMeanie {
	
	private static List<DominoSet> cyclicDistributeDominoesToPlayers(DominoSet allDominoes, int playerCount) {
		
		ListIterator<Domino> iter = allDominoes.getList().listIterator();
		List<DominoSet> playerHands = new ArrayList<DominoSet>();
		
		IntStream.range(0, playerCount).forEachOrdered(p -> {
			playerHands.add(new DominoSet(new ArrayList<Domino>()));
		});
		
		RepeatingListIterator<DominoSet> playersIter = new RepeatingListIterator<DominoSet>(playerHands);
		
		while (iter.hasNext()) {
			playersIter.next().getList().add(iter.next());
		}
		
		return playerHands;
	}
	
	private static List<DominoSet> splitDominoSetToPlayers(DominoSet allDominoes, int playerCount) {
		
		final int numberOfDominoes = allDominoes.getList().size();
		ListIterator<Domino> iter = allDominoes.getList().listIterator();
		ArrayList<Domino> toGivePlayer = new ArrayList<Domino>();
		
		List<DominoSet> playerHands = new ArrayList<DominoSet>();
		
		int div = 1;
		int given = 0;
		
		while (iter.hasNext()) {
			toGivePlayer.add(iter.next());
			int currentHandSize = toGivePlayer.size();
			if (currentHandSize >= numberOfDominoes * div / playerCount - given) {
				given += currentHandSize;
				playerHands.add(new DominoSet((List<Domino>)toGivePlayer.clone()));
				toGivePlayer.clear();
				div++;
			}
		}
		
		return playerHands;
	}

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
		allDominoes.shuffle(randSeed);
		List<DominoSet> playerHands = cyclicDistributeDominoesToPlayers(allDominoes, playerCount);
		
		System.out.println(playerHands);
		
		
		
		input.close();
		
		Map<String,Integer> m = new TreeMap<String,Integer>();
		System.out.println(m.put("8",1));
		System.out.println(m.put("8",2));
		m.put("8",3);
		m.put("5",4);
		m.put("6",5);
		m.put("2",6);
		System.out.println(m);
	}
}

class RepeatingListIterator<T> {
	
	private List<T> list;
	private ListIterator<T> iter;
	
	public RepeatingListIterator(List<T> l) {
		list = l;
		iter = l.listIterator();
	}
	
	public T next() {
		if (!iter.hasNext())
			iter = list.listIterator();
		
		return (T)iter.next();
	}
	
	public boolean hasNext() {
		return list.size() > 0;
	}
}
