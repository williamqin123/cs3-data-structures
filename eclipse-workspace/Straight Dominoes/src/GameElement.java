
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class GameElement {
	
	protected Animation animation;
	
	public void setAnimation(Animation a) {animation = a;}
	public Animation getAnimation() {return animation;}
	
	protected boolean visible = true;
	
	public void setVisible(boolean v) {visible = v;}
	public boolean isVisible() {return visible;}
	
	protected Image rawImage;
	
	protected double rotation = 0;
	protected int width = 0;
	protected int height = 0;
	protected int x = 0;
	protected int y = 0;
	protected int relativeOffsetX = 0;
	protected int relativeOffsetY = 0;
	
	List<GameElement> linked = new ArrayList<GameElement>();
	
	public GameElement(Image raw) {
		rawImage = raw;
	}
	
	public void rotate(double angle) {
		rotation = (rotation + angle) % 360;
	}
	public void setRotation(double angle) {
		rotation = angle;
	}
	public double getRotation() {
		return rotation;
	}
	
	public Image getRawImage() {
		return rawImage;
	}
	
	public void setWidth(int w) {width = w;}
	public int getWidth() {return width;}
	
	public void setHeight(int h) {height = h;}
	public int getHeight() {return height;}
	
	public void setX(int x) {this.x = x;}
	public int getX() {return x;}
	
	public void setY(int y) {this.y = y;}
	public int getY() {return y;}
	
	public void centerX(int x) {
		this.x = x - width / 2;
	}
	public void centerY(int y) {
		this.y = y - height / 2;
	}
	
	public void setDX(int dx) {relativeOffsetX = dx;}
	public int getDX() {return relativeOffsetX;}
	
	public void setDY(int dy) {relativeOffsetY = dy;}
	public int getDY() {return relativeOffsetY;}
}
