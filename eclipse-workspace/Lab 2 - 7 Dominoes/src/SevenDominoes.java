
import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

public class SevenDominoes extends JFrame {

	private final static String NAME = "7 Dominoes";
	
	private final static int DOMINOS_TO_SHOW = 7;
	private final static int MAX_PIPS_PER_TILE = 6;
	private final static int DOMINO_SET_SIZE = (int) (Math.pow(MAX_PIPS_PER_TILE + 1, 2) / 2.0 + (MAX_PIPS_PER_TILE + 1) / 2.0);
	
	BufferedImage[] dominoes = new BufferedImage[DOMINOS_TO_SHOW];
	
	public Object pickRandNoReplace(ArrayList l) {
		int index = (int) (Math.random() * l.size());
		Object chosen = l.get(index);
		l.remove(index);
		return chosen;
	}

	public SevenDominoes() throws IOException {
		super(NAME);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit.getDefaultToolkit().setDynamicLayout(true);
		
		ArrayList<String> randomChoices = new ArrayList<String>();
		
		for (int n_tile1 = 0; n_tile1 <= MAX_PIPS_PER_TILE; n_tile1++) {
			for (int n_tile2 = 0; n_tile2 <= n_tile1; n_tile2++) {
				randomChoices.add(n_tile1 + "" + n_tile2);
			}
		}
		
		for (int j = 0; j < DOMINOS_TO_SHOW; j++) {
			dominoes[j] = (BufferedImage)ImageIO.read(this.getClass().getResource("pictures/domino" + pickRandNoReplace(randomChoices) + ".png"));
		}
		
		new JPanel() {

			public JPanel addToFrame() {
				SevenDominoes.this.getContentPane().add(this);
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
				
				g2.rotate(Math.toRadians(90));
			    
				for (int k = 0; k < DOMINOS_TO_SHOW; k++) {
					g2.drawImage(SevenDominoes.this.dominoes[k], 275, -50 - 100 * (k + 1), 200, 100, null);
				}
			}
		}.addToFrame().setPreferredSize(new Dimension(800, 500));
		
		pack();
		setVisible(true);
	}
}
