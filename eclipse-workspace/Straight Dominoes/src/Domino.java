import java.awt.Image;

public class Domino extends GameElement {
	
	private int[] val = new int[2];

	public Domino(int n1, int n2) {
		
		super(null);
		
		Image img = StraightDominoesApp.storage.get("new-domino-" + n1 + n2 + ".png");
		rawImage = img;
		
		width = 100;
		height = 50;
		
		GameElement hl = new GameElement(StraightDominoesApp.storage.get("domino-shading.png"));
		hl.setWidth(100);
		hl.setHeight(50);
		hl.followParentOffset = true;
		children.add(hl);
		hl.parent = this;
		
		GameElement depth = new GameElement(StraightDominoesApp.storage.get("domino-3d.png"));
		depth.setWidth(100);
		depth.setHeight(50);
		depth.setDY(5);
		depth.followParentOffset = true;
		children.add(depth);
		depth.parent = this;
		
		GameElement sh = new GameElement(StraightDominoesApp.storage.get("domino-shadow.png"));
		sh.setWidth(120);
		sh.setHeight(70);
		sh.setDX(-10);
		sh.setDY(-5);
		children.add(sh);
		sh.parent = this;
	}

}
