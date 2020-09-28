
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class DominoPicture  extends JFrame {
	
	private final static String NAME = "Domino Picture";
	
	BufferedImage dominantDomDomino;

	public DominoPicture() throws IOException {
		super(NAME);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit.getDefaultToolkit().setDynamicLayout(true);
		
		dominantDomDomino = (BufferedImage)ImageIO.read(this.getClass().getResource("pictures/domino63.png"));
		
		new JPanel() {
			
			public JPanel addToFrame() {
				DominoPicture.this.getContentPane().add(this);
				return this;
			}
			
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D)g;
				
				// Upps graphics quality settings
			    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			    g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
			    g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
			    g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
			    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			    g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
				
			    BufferedImage im = DominoPicture.this.dominantDomDomino;
			    
				for (int r = 0; r < 360; r += 90) {
					g2.drawImage(im, (r == 270) ? -200 : ((r == 180) ? -500 : 0), (r % 180 == 90) ? ((r / 90 - 1) * (r - 180) / 90 * 250 + ((r == 90) ? -300 : 0)) : ((r == 180) ? -100 : 0), 200, 100, null);
					
					g2.rotate(Math.toRadians(90));
				}
			}
		}.addToFrame().setPreferredSize(new Dimension(600, 200));
		
		pack();
		setVisible(true);
	}
}

