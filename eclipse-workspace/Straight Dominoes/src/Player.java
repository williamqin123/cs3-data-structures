import java.util.ArrayList;
import java.util.List;

public class Player {
	
	public ArrayList<Domino> hand = new ArrayList<Domino>();
	
	private TextLabel associatedScoreLabel;
	
	
	
	private int score = 0;
	
	
	
	public void linkScoreLabel(TextLabel t) {
		associatedScoreLabel = t;
	}
	public TextLabel getScoreLabel() {
		return associatedScoreLabel;
	}
	
	
	
	public int getScore() { return score; }
	
	public void addPoints(int pts) {
		score += pts;
	}
}
