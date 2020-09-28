
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class CornerImg extends JPanel {
	
	private BufferedImage[] images;
	private int activeImageIndex = 0;
	
	public static final int DEFAULT_SIZE = 300;
	
	public CornerImg(String... urls) {
		images = new BufferedImage[urls.length];
		
		for (int i = 0; i < urls.length; i++) {
			try {
				images[i] = (BufferedImage)ImageIO.read(this.getClass().getResource(urls[i]));
			}
			catch (IOException e) {
				System.out.println(e);
			}
		}
		
		setPreferredSize(new Dimension(DEFAULT_SIZE, DEFAULT_SIZE));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				CornerImg.this.activeImageIndex = (activeImageIndex + 1) % images.length;
				CornerImg.this.repaint();
			}
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		g2.drawImage(images[activeImageIndex], 0, 0, getWidth(), getHeight(), null);
	}
}
