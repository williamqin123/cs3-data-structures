
// Based on Lab 04 - Line of Dominoes

public class Domino implements Comparable<Domino> {

	private int leftPips, rightPips;
	
	public Domino(int leftPips, int rightPips) {
		setLeftPips(leftPips);
		setRightPips(rightPips);
	}

	public int getLeftPips() {
		return leftPips;
	}

	public void setLeftPips(int leftPips) {
		this.leftPips = leftPips;
	}

	public int getRightPips() {
		return rightPips;
	}

	public void setRightPips(int rightPips) {
		this.rightPips = rightPips;
	}
	
	// 180-degree flip
	public void rotate() {
		// swaps variables
		leftPips = leftPips + rightPips;   
		rightPips = leftPips - rightPips;   
        leftPips = leftPips - rightPips;  
	}
	
	@Override
	public String toString() {
		return "[" + leftPips + "|" + rightPips + "]";
	}

	@Override
	public int compareTo(Domino o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
