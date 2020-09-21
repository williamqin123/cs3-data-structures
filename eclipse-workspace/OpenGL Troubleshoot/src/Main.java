
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class Main extends JPanel {
	
	public Main() {
		JFrame frame = new JFrame("OpenGL off");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setPreferredSize(new Dimension(800, 600));
		
		frame.add(this);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

        g2.setPaint(Color.RED);
        g2.setStroke(new BasicStroke(5.0f));
        g2.draw(new Ellipse2D.Double(0, 0, 800, 600));
	}

	public static void main(String[] args) {

		// Enables hardware acceleration
		System.setProperty("sun.java2d.opengl", "true");
		
		new Main();

	}

}
