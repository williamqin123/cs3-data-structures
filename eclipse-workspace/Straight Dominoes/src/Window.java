
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Window extends JFrame {
	
	public static final int DEFAULT_WIDTH = 1200;
	public static final int DEFAULT_HEIGHT = 800;
	
	
	
	public Window(String framename){
		
		super(framename);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		GameViewport gameArea = new GameViewport();
		
		StraightDominoesApp.game = new Game(gameArea);
		
		add(gameArea);
		
		addMouseListener(gameArea);
		addMouseMotionListener(gameArea);
		
		setVisible(true);
		
		StraightDominoesApp.game.start();
		
		pack();
	}
}
