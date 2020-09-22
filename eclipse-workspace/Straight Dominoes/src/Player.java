import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Player {
	
	public ArrayList<Domino> hand = new ArrayList<Domino>();
	
	HashMap<String, Object> linkedObjects = new HashMap<String, Object>();
	
	
	
	private int roundScore = 0;
	private int totalScore = 0;
	
	
	
	public int getTotalScore() { return totalScore; }
	public int getCurrentRoundScore() { return roundScore; }
	
	public void addPoints(int pts) {
		roundScore += pts;
		totalScore += pts;
	}
	
	public void resetRoundScore() {
		roundScore = 0;
	}
}
