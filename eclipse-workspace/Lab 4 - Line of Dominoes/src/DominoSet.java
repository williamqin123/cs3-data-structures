
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.stream.IntStream; 

public class DominoSet {

	//setOfDominoes is a List of Domino
	private List<Domino> setOfDominoes = new ArrayList<Domino>();
	
	public List<Domino> getList() {
		return setOfDominoes;
	}
	
	public DominoSet(List<Domino> dominoList) {
		setOfDominoes = dominoList;
	}
	
	public DominoSet(int maxPips) {
		// avoids using indexes in for loops
		IntStream.range(0, maxPips + 1).forEachOrdered(p1 -> {
			IntStream.range(0, p1 + 1).forEachOrdered(p2 -> {
			    Domino d = new Domino(p1, p2);
			    if (Math.random() >= 0.5) {
			    	d.rotate();
			    }
			    setOfDominoes.add(d);
			});
		});
	}
	
	//this is the only method allowed to use indices.
	public void shuffle(int randSeed)
	{
		Random rand = new Random(randSeed);
		for(int i=0; i<setOfDominoes.size(); i++)
		{
			if(rand.nextBoolean())
				setOfDominoes.get(i).rotate();
			int k = rand.nextInt(setOfDominoes.size());
			Domino temp = setOfDominoes.get(k);
			setOfDominoes.set(k,setOfDominoes.get(i));
			setOfDominoes.set(i, temp);
		}
	}
	
	@Override
	public String toString() {
		String dominoSetString = "";
		ListIterator<Domino> iter = setOfDominoes.listIterator();
		while (iter.hasNext()) {
			dominoSetString += iter.next().toString();
		}
		return dominoSetString;
	}
}
