import java.awt.Image;

public class Domino extends GameElement {
	
	private int[] val = new int[2];

	public Domino(int n1, int n2) {
		
		super(null);
		
		Image img = StraightDominoesApp.storage.get("domino" + n1 + n2 + ".png");
		rawImage = img;
		
		width = 100;
		height = 50;
	}

}
