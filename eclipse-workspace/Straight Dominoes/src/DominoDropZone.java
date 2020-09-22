import java.awt.Dimension;

public class DominoDropZone extends GameElement {
	
	private String branch;
	
	private Dimension vector;
	
	public DominoDropZone(int x, int y, String branch, Dimension vector) {
		super(StraightDominoesApp.storage.get("blue-rect.png"));
		
		this.branch = branch;
		
		this.vector = vector;
		
		int[] pos = Game.cartesian(x, y);
		
		width = 110;
		height = 60;
		
		this.x = pos[0];
		this.y = pos[1];
		
		this.relativeOffsetX = -55;
		this.relativeOffsetY = -30;
		
		StraightDominoesApp.window.viewport.glDropZones.addDrawable(this);
		
		StraightDominoesApp.window.viewport.glDropZones.repaint();
	}
	
	public void remove() {
		StraightDominoesApp.window.viewport.glDropZones.removeDrawable(this);
	}

	public String getBranchName() {
		return branch;
	}
	
	public Dimension getVec() {
		return vector;
	}
}
