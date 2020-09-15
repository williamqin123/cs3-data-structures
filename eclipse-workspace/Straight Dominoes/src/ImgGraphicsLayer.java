
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class ImgGraphicsLayer extends LayerPanel {
	
	boolean animationEnabled = false;
	
	private List<GameElement> objects = new ArrayList<GameElement>();
	
	public ImgGraphicsLayer() {
		super();
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g); // clear
		
		Graphics2D g2 = (Graphics2D)g;
		
		for (GameElement img : objects) {
			if (!img.isVisible()) continue;
			
			AffineTransform initialTransform = g2.getTransform();
			
			g2.translate(img.getX() + img.getDX() + img.getWidth() / 2, img.getY() + img.getDY() + img.getHeight() / 2);
			g2.rotate(Math.toRadians(img.getRotation()));
			
			g2.drawImage(img.getRawImage(), -img.getWidth() / 2, -img.getHeight() / 2, img.getWidth(), img.getHeight(), null);
			
			g2.setTransform(initialTransform);
		}
		
	}
	
	public void addDrawable(GameElement img) {
		objects.add(img);
	}
	public void removeDrawable(GameElement img) {
		objects.remove(img);
	}
	public void clearRenderList() {
		objects.clear();
	}
	
}
