
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

public class TextLabel {
	
	protected Font font;
	
	protected String text;
	
	protected int x, y;
	
	protected Color color;
	
	// String flags that affect text style when present
	public ArrayList<String> flags = new ArrayList<String>();
	
	public TextLabel(String text, String typeface, int fontStyle, int fontSize, Color textColor, int xPos, int yPos) {
		font = new Font(typeface, fontStyle, fontSize);
		x = xPos;
		y = yPos;
		color = textColor;
		this.text = text;
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public Color getColor() {
		
		if (flags.contains("scoreboard-active")) {
			return Color.RED;
		}
		
		return color;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String str) {
		text = str;
	}

	public Font getFont() {
		
		if (flags.contains("scoreboard-active")) {
			return font.deriveFont(Font.ITALIC | Font.BOLD);
		}
		
		return font;
	}
	
	public void addFlag(String flag) {
		if (!flags.contains(flag)) flags.add(flag);
	}
	public void removeFlag(String flag) {
		if (flags.contains(flag)) flags.remove(flag);
	}
}
