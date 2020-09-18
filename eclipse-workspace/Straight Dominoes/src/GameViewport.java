
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GameViewport extends JLayeredPane implements MouseListener, MouseMotionListener {
	
	public double getScale() {
		return getPreferredSize().getWidth() / Window.DEFAULT_WIDTH;
	}
	
	public LayerPanel layers[] = new LayerPanel[10];
	
	public ImgGraphicsLayer glBackground = new ImgGraphicsLayer();
	public ImgGraphicsLayer glShadow = new ImgGraphicsLayer();
	public ImgGraphicsLayer glDominoes = new ImgGraphicsLayer();
	public ImgGraphicsLayer glDropZones = new ImgGraphicsLayer();
	public ImgGraphicsLayer glHandShadow = new ImgGraphicsLayer();
	public ImgGraphicsLayer glHandDominoes = new ImgGraphicsLayer();
	public ImgGraphicsLayer glActiveShadow = new ImgGraphicsLayer();
	public ImgGraphicsLayer glActiveDominoes = new ImgGraphicsLayer();
	public TextGraphicsLayer glHUD = new TextGraphicsLayer();
	public ImgGraphicsLayer glParticles = new ImgGraphicsLayer();
	
	
	private List<ClickArea> clickZones = new ArrayList<ClickArea>();
	
	public void addClickZone(int x0, int y0, int x1, int y1, GameElement target) {
		ClickArea region = new ClickArea(x0, y0, x1, y1);
		region.setTarget(target);
		clickZones.add(region);
	}
	public void addClickZone(ClickArea z) {
		clickZones.add(z);
	}
	public void removeClickZone(ClickArea zone) {
		clickZones.remove(zone);
	}
	public ClickArea click(int x, int y) {
		for (ClickArea zone : clickZones) {
			if (x >= zone.x0 * getScale() && x <= zone.x1 * getScale()
			&&  y >= zone.y0 * getScale() && y <= zone.y1 * getScale()) {
				return zone;
			}
		}
		return null;
	}
	
	public void rescaleLayers() {
		for (LayerPanel layer : layers) {
			layer.setSize((int) (Window.DEFAULT_WIDTH * getScale()), (int) (Window.DEFAULT_HEIGHT * getScale()));
			layer.setBounds(0, 0, (int) (Window.DEFAULT_WIDTH * getScale()), (int) (Window.DEFAULT_HEIGHT * getScale())); 
		}
	}
	
	
	
	public GameViewport(){
		
		super();
		
		add(glBackground, new Integer(0));
		add(glShadow, new Integer(1));
		add(glDominoes, new Integer(2));
		add(glDropZones, new Integer(3));
		add(glHandShadow, new Integer(4));
		add(glHandDominoes, new Integer(5));
		add(glActiveShadow, new Integer(6));
		add(glActiveDominoes, new Integer(7));
		add(glHUD, new Integer(8));
		add(glParticles, new Integer(9));
		
		layers[0] = glBackground;
		layers[1] = glShadow;
		layers[2] = glDominoes;
		layers[3] = glDropZones;
		layers[4] = glHandShadow;
		layers[5] = glHandDominoes;
		layers[6] = glActiveShadow;
		layers[7] = glActiveDominoes;
		layers[8] = glHUD;
		layers[9] = glParticles;
		
		addMouseListener(this);
		addMouseMotionListener(this);
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		/*
		ClickArea zone = click(e.getX(), e.getY());
		
		if (zone == null) {
			if (SwingUtilities.isLeftMouseButton(e)) {
				StraightDominoesApp.game.handleClickEvent(); // generic, targetless left-click
			}
			else if (SwingUtilities.isRightMouseButton(e)) {
				StraightDominoesApp.game.handleRightClickEvent(); // generic, targetless right-click
			}
			return;
		}
		
		if (SwingUtilities.isLeftMouseButton(e)) {
			StraightDominoesApp.game.handleClickEvent(zone.getTarget(), zone);
		}
		else if (SwingUtilities.isRightMouseButton(e)) {
			StraightDominoesApp.game.handleRightClickEvent(zone.getTarget(), zone);
		}
		*/
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		
		ClickArea zone = click(e.getX(), e.getY());
		
		if (zone == null) {
			if (SwingUtilities.isLeftMouseButton(e)) {
				StraightDominoesApp.game.handleClickEvent(); // generic, targetless left-click
			}
			else if (SwingUtilities.isRightMouseButton(e)) {
				StraightDominoesApp.game.handleRightClickEvent(); // generic, targetless right-click
			}
			return;
		}
		
		if (SwingUtilities.isLeftMouseButton(e)) {
			StraightDominoesApp.game.handleClickEvent(zone.getTarget(), zone);
		}
		else if (SwingUtilities.isRightMouseButton(e)) {
			StraightDominoesApp.game.handleRightClickEvent(zone.getTarget(), zone);
		}
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		StraightDominoesApp.game.handleCursorMoveEvent((int) (e.getX() / getScale()), (int) (e.getY() / getScale()));
		
	}

}
