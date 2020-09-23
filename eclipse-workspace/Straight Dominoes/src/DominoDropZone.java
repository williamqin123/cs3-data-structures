import java.awt.Dimension;
import java.util.LinkedList;

public class DominoDropZone extends GameElement {
	
	public LinkedList<Domino> branch;
	
	private Dimension vector;
	
	public DominoDropZone(int x, int y, LinkedList branch, int orientation) {
		super(StraightDominoesApp.storage.get("blue-rect.png"));
		
		this.branch = branch;
		
		this.vector = vector;
		
		//int[] pos = Game.cartesian(x, y);
		
		width = 110;
		height = 60;
		
		this.x = x;
		this.y = y;
		
		this.relativeOffsetX = -30;
		this.relativeOffsetY = -30;
		
		rotationOriginX = 30;
		rotationOriginY = 30;
		
		rotation = orientation;
		
		StraightDominoesApp.window.viewport.glDropZones.addDrawable(this);
		
		StraightDominoesApp.window.viewport.glDropZones.repaint();
	}
	
	public void remove() {
		StraightDominoesApp.window.viewport.glDropZones.removeDrawable(this);
	}
	
	public Dimension getVec() {
		return vector;
	}
}
