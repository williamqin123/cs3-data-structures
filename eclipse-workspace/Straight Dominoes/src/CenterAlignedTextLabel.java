
import java.awt.Color;
import java.awt.*;

public class CenterAlignedTextLabel extends TextLabel {

	// location to align to
	private int intendedX;
	private int intendedY;
	
	public CenterAlignedTextLabel(String text, String typeface, int fontStyle, int fontSize, Color textColor, int xPos, int yPos) {
		super(text, typeface, fontStyle, fontSize, textColor, xPos, yPos);
		
		intendedX = xPos;
		intendedY = yPos;
		
		setText(text);
	}
	
	@Override
	public void setText(String str) {
		text = str;
		FontMetrics metrics = StraightDominoesApp.window.viewport.glHUD.getGraphics().getFontMetrics(font);
		
		x = intendedX - metrics.stringWidth(text) / 2; // center-aligns text
	}
	
}
