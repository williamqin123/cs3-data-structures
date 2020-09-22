
import java.awt.*;
import java.awt.event.*;
import java.util.EventObject;

import javax.swing.*;

public class Window extends JFrame {
	
	public static final int DEFAULT_WIDTH = 1200;
	public static final int DEFAULT_HEIGHT = 800;
	
	public static final double ASPECT_RATIO = 1.5;
	
	public GameViewport viewport;
	
	
	
	private void resize(EventObject e) {
		if (e.getSource() != Window.this) {
			return;
		}
		
		revalidate();
		
	    Rectangle b = getContentPane().getBounds();
	    
	    Dimension d;
	    
	    if (b.getWidth() <= ASPECT_RATIO * b.getHeight()) {
	    	d = new Dimension((int) b.getWidth(), (int) (b.getWidth() / ASPECT_RATIO));
	    }
	    else {
	    	d = new Dimension((int) (b.getHeight() * ASPECT_RATIO), (int) b.getHeight());
	    }
		
		viewport.setPreferredSize(d);
		viewport.rescaleLayers();
		viewport.repaint();
	}
	
	
	
	public Window(String framename){
		
		super(framename);
		
		final Dimension dim = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Toolkit.getDefaultToolkit().setDynamicLayout(false);
		
		setBackground(Color.BLACK);
		getContentPane().setBackground(Color.BLACK);
		
		getContentPane().setPreferredSize(dim);
		pack();
		
		
		
		viewport = new GameViewport();
		viewport.setPreferredSize(dim);
		
		
		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.add(Box.createVerticalGlue());
		viewport.setAlignmentY(Component.CENTER_ALIGNMENT);
		verticalBox.add(viewport);
		verticalBox.add(Box.createVerticalGlue());
		
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.add(Box.createHorizontalGlue());
		verticalBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		horizontalBox.add(verticalBox);
		horizontalBox.add(Box.createHorizontalGlue());
		
		add(horizontalBox);
		
		//addMouseListener(viewport);
		//addMouseMotionListener(viewport);
		
		setVisible(true);
		
		addComponentListener(new ComponentAdapter() {
			
			public void componentResized(ComponentEvent e) {
				resize(e);
			}
		});
		
		addWindowStateListener(new WindowStateListener() {
		   public void windowStateChanged(WindowEvent e) {
			   resize(e);
		   }
		});
	}
}
