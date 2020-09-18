import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TextGraphicsLayer extends LayerPanel{
	
	private List<TextLabel> objects = new ArrayList<TextLabel>();
	
	public TextGraphicsLayer() {
		super();
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g); // clear
		
		Graphics2D g2 = (Graphics2D)g;
		
		double scale = StraightDominoesApp.window.viewport.getScale();
		
		// Improves text quality drastically
		g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		
		for (TextLabel txt : objects) {
			
			g2.setColor(txt.getColor());
			
			Font scaledFont = txt.getFont();
			scaledFont = scaledFont.deriveFont((float) (scaledFont.getSize() * scale));
			
			g2.setFont(scaledFont);
			
			g2.drawString(txt.getText(), (int)(txt.getX() * scale), (int)(txt.getY() * scale));
			
		}
		
	}
	
	public void addLabel(TextLabel txt) {
		objects.add(txt);
	}
	public void removeLabel(TextLabel txt) {
		objects.remove(txt);
	}
	public void deleteAll() {
		objects.clear();
	}
	
}
