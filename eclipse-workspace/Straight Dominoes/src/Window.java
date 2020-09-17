
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Window extends JFrame {
	
	public static final int DEFAULT_WIDTH = 1200;
	public static final int DEFAULT_HEIGHT = 800;
	
	
	
	public Window(String framename){
		
		super(framename);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Toolkit.getDefaultToolkit().setDynamicLayout(false);
		
		Dimension size = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		getContentPane().setPreferredSize(size);
		getContentPane().setMinimumSize(size);
		getContentPane().setMaximumSize(size);
		
		GameViewport gameArea = new GameViewport();
		
		StraightDominoesApp.game = new Game(gameArea);
		
		add(gameArea);
		
		addMouseListener(gameArea);
		addMouseMotionListener(gameArea);
		
		setVisible(true);
		
		pack();
		
		StraightDominoesApp.game.start();
		
		addComponentListener(new ComponentAdapter() 
		{  
			public void componentResized(ComponentEvent arg0) {
			    Rectangle b = arg0.getComponent().getBounds();
			    ((JFrame) arg0.getComponent()).getContentPane().setPreferredSize(new Dimension(b.width, (int) (b.width * 2.0/3.0)));
				
				pack();

			}
		});
	}
}
