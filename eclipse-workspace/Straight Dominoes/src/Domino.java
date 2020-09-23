import java.awt.Dimension;
import java.awt.Image;

public class Domino extends GameElement {
	
	public static final int DOMINO_DEPTH = 5;
	
	public final static int INLINE = 0;
	public final static int PERPENDICULAR = 1;
	public int type = INLINE;
	
	private int[] val = new int[2];
	
	public int openTilePips = 0;
	
	public int[] getPips() {
		return val;
	}
	
	public int getTotalPipCount() {
		return val[0] + val[1];
	}
	
	public int getHalfPips(int rot) {
		
		int dRot = Math.floorMod(((int) rotation - rot), 360);
		
		//System.out.println(dRot);
		
		if (dRot == 0) {
			return val[1];
		}
		else if (dRot == 180) {
			return val[0];
		}
		
		return -1;
	}
	
	private boolean placed = false;
	
	public void setPlaced() {
		placed = true;
	}
	
	public boolean isPlaced() {
		return placed;
	}

	public Domino(int n1, int n2) {
		
		super(null);
		
		val[0] = n1;
		val[1] = n2;
		
		Image img = StraightDominoesApp.storage.get("new-domino-" + n1 + n2 + ".png");
		rawImage = img;
		
		width = 100;
		height = 50;
		
		setDY(-5);
		
		GameElement hl = new GameElement(StraightDominoesApp.storage.get("domino-shading-h.png"), StraightDominoesApp.storage.get("domino-shading-v.png"), new Integer[]{90, 270});
		hl.setWidth(100);
		hl.setHeight(100);
		hl.setDY(-25);
		hl.followParentOffset = true;
		hl.relativeZindex = 1;
		children.add(hl);
		hl.parent = this;
		
		GameElement depth = new GameElement(StraightDominoesApp.storage.get("domino-3d-h.png"), StraightDominoesApp.storage.get("domino-3d-v.png"), new Integer[]{90, 270});
		depth.setWidth(100);
		depth.setHeight(100);
		depth.setDY(-20);
		depth.followParentOffset = true;
		depth.relativeZindex = -1;
		children.add(depth);
		depth.parent = this;
		
		GameElement sh = new GameElement(StraightDominoesApp.storage.get("domino-shadow.png"));
		sh.setWidth(120);
		sh.setHeight(70);
		sh.setDX(-10);
		sh.setDY(-10);
		sh.followParentRotation = true;
		sh.relativeZindex = -2;
		children.add(sh);
		sh.parent = this;
	}

}
