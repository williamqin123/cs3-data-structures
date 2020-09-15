
import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class ImgGraphicsLayer extends LayerPanel {
	
	private List<GameElement> objects = new ArrayList<GameElement>();
	
	public ImgGraphicsLayer() {
		super();
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g); // clear
		
		Graphics2D g2 = (Graphics2D)g;
		
		for (GameElement img : objects) {
			if (!img.isVisible()) continue;
			g2.drawImage(img.getRawImage(), img.getX() + img.getDX(), img.getY() + img.getDY(), img.getWidth(), img.getHeight(), null);
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
