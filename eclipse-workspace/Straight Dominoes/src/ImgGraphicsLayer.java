
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
		
		double scale = StraightDominoesApp.window.viewport.getScale();
		
		
		// Upps graphics quality settings
		
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
	    g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	    g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		
		for (GameElement img : objects) {
			if (!img.isVisible()) continue;
			
			AffineTransform initialTransform = g2.getTransform();
			
			g2.translate((img.getX() + img.getDX() + img.getWidth() / 2) * scale, (img.getY() + img.getDY() + img.getHeight() / 2) * scale);
			g2.rotate(Math.toRadians(img.getRotation()));
			
			g2.drawImage(img.getRawImage(), (int) (-img.getWidth() / 2 * scale), (int) (-img.getHeight() / 2 * scale), (int) (img.getWidth() * scale), (int) (img.getHeight() * scale), null);
			
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
