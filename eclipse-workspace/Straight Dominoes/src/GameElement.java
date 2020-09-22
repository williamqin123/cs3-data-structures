
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameElement {
	
	protected Animation animation;
	
	public void setAnimation(Animation a) {animation = a;}
	public Animation getAnimation() {return animation;}
	
	protected boolean visible = true;
	
	public void setVisible(boolean v) {visible = v;}
	public boolean isVisible() {return visible;}
	
	protected Image rawImage;
	protected Image altImage; // alternative image that activates when the parent is rotated a certain way
	
	protected List<Integer> specialParentOrientations;
	
	protected double rotation = 0;
	protected int width = 0;
	protected int height = 0;
	protected int x = 0;
	protected int y = 0;
	protected int relativeOffsetX = 0;
	protected int relativeOffsetY = 0;
	
	protected List<GameElement> children = new ArrayList<GameElement>();
	protected GameElement parent;
	
	public boolean followParentPosition = true;
	public boolean followParentOffset = false;
	public boolean followParentRotation = false;
	
	public int relativeZindex = 0;
	
	private ClickArea clickArea;
	
	public ImgGraphicsLayer currentLayer;
	
	public GameElement(Image raw) {
		rawImage = raw;
	}
	
	public void setImage(String fil) {
		rawImage = StraightDominoesApp.storage.get(fil);
	}
	
	public GameElement(Image raw, Image alt, Integer[] orientations) {
		rawImage = raw;
		altImage = alt;
		specialParentOrientations = Arrays.asList(orientations);
	}
	
	public void rotate(double angle) {
		rotation = (rotation + angle) % 360;
	}
	public void setRotation(double angle) {
		rotation = angle;
	}
	public double getRotation() {
		return (parent == null || !followParentRotation) ? rotation : rotation + parent.getRotation();
	}
	
	public Image getRawImage() {
		return altImage == null ? rawImage : ((specialParentOrientations.contains((int)parent.rotation)) ? altImage : rawImage);
	}
	
	public void setWidth(int w) {width = w;}
	public int getWidth() {return width;}
	
	public void setHeight(int h) {height = h;}
	public int getHeight() {return height;}
	
	public void setX(int x) {this.x = x;}
	public int getX() {return (parent == null || !followParentPosition) ? x : x + parent.getX();}
	
	public void setY(int y) {this.y = y;}
	public int getY() {return (parent == null || !followParentPosition) ? y : y + parent.getY();}
	
	public void centerX(int x) {
		this.x = x - width / 2;
	}
	public void centerY(int y) {
		this.y = y - height / 2;
	}
	
	public void setDX(int dx) {relativeOffsetX = dx;}
	public int getDX() {return (parent == null || !followParentOffset) ? relativeOffsetX : relativeOffsetX + parent.getDX();}
	
	public void setDY(int dy) {relativeOffsetY = dy;}
	public int getDY() {return (parent == null || !followParentOffset) ? relativeOffsetY : relativeOffsetY + parent.getDY();}
	
	public void setLayer(int layerIndex) {
		if (currentLayer != null) currentLayer.removeDrawable(this);
		ImgGraphicsLayer layer = (ImgGraphicsLayer)StraightDominoesApp.window.viewport.layers[layerIndex];
		layer.addDrawable(this);
		currentLayer = layer;
		
		for (GameElement child : children) {
			child.setLayer(layerIndex + child.relativeZindex);
		}
	}
	
	public ClickArea getClickArea() {
		return clickArea;
	}
	public void setClickArea(ClickArea clickArea) {
		this.clickArea = clickArea;
	}
}
