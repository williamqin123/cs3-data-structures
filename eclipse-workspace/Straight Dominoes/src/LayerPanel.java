import javax.swing.JPanel;

public class LayerPanel extends JPanel {
	public LayerPanel() {
		setSize(Window.DEFAULT_WIDTH, Window.DEFAULT_HEIGHT);
		setBounds(0, 0, Window.DEFAULT_WIDTH, Window.DEFAULT_HEIGHT); 
		setOpaque(false);
	}
}
