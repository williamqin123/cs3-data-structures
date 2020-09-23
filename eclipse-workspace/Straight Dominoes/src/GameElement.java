
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameElement {
	
	public final static int DIRECTION_NORTH = 270;
	public final static int DIRECTION_EAST = 0;
	public final static int DIRECTION_SOUTH = 90;
	public final static int DIRECTION_WEST = 180;
	
	protected int rotationOriginX = (int) Double.NEGATIVE_INFINITY;
	protected int rotationOriginY = (int) Double.NEGATIVE_INFINITY;
	
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
	
	public void setRotationOriginX(int xo) {rotationOriginX = xo;}
	public int getRotationOriginX() {return (rotationOriginX == (int) Double.NEGATIVE_INFINITY) ? getWidth() / 2 : rotationOriginX;}
	
	public void setRotationOriginY(int yo) {rotationOriginY = yo;}
	public int getRotationOriginY() {return (rotationOriginY == (int) Double.NEGATIVE_INFINITY) ? getHeight() / 2 : rotationOriginY;}
	
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
	
	
	
	// These should use trig to work with all angles, but I'm lazy
	
	public int getTopBound() {
		int yVal = getY() + getDY();
		switch ((int) rotation) {
		case 0:
			return yVal;
		case 90:
			return yVal - (getRotationOriginX() - getRotationOriginY());
		case 180:
			return yVal - (getHeight() - 2 * getRotationOriginY());
		case 270:
			return yVal - (getWidth() - getRotationOriginX() - getRotationOriginY());
		default:
			return 0;
		//	throw new Exception("Bounds supported for right-angle rotations only");
		}
	}
	public int getLeftBound() {
		int xVal = getX() + getDX();
		switch ((int) rotation) {
		case 0:
			return xVal;
		case 90:
			return xVal - (getHeight() - getRotationOriginY() - getRotationOriginX());
		case 180:
			return xVal - (getWidth() - 2 * getRotationOriginX());
		case 270:
			return xVal - (getRotationOriginY() - getRotationOriginX());
		default:
			return 0;
		//	throw new Exception("Bounds supported for right-angle rotations only");
		}
	}
	public int getBottomBound() {
		return getTopBound() + ((rotation % 180 == 0) ? getHeight() : getWidth());
	}
	public int getRightBound() {
		return getLeftBound() + ((rotation % 180 == 0) ? getWidth() : getHeight());
	}
	public int getBoundsCenterX() {
		return (getLeftBound() + getRightBound()) / 2;
	}
	public int getBoundsCenterY() {
		return (getTopBound() + getBottomBound()) / 2;
	}
}
