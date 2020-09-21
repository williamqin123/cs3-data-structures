
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GameViewport extends JLayeredPane implements MouseListener, MouseMotionListener {
	
	public double getScale() {
		return getPreferredSize().getWidth() / Window.DEFAULT_WIDTH;
	}
	
	public ImgGraphicsLayer glBackground = new ImgGraphicsLayer();
	
	public ImgGraphicsLayer glShadow = new ImgGraphicsLayer();
	public ImgGraphicsLayer glThickness = new ImgGraphicsLayer();
	public ImgGraphicsLayer glDominoes = new ImgGraphicsLayer();
	public ImgGraphicsLayer glOverlays = new ImgGraphicsLayer();
	
	public ImgGraphicsLayer glDropZones = new ImgGraphicsLayer();
	
	public ImgGraphicsLayer glHandShadow = new ImgGraphicsLayer();
	public ImgGraphicsLayer glHandThickness = new ImgGraphicsLayer();
	public ImgGraphicsLayer glHandDominoes = new ImgGraphicsLayer();
	public ImgGraphicsLayer glHandOverlays = new ImgGraphicsLayer();
	
	public TextGraphicsLayer glHUD = new TextGraphicsLayer();
	
	public ImgGraphicsLayer glActiveShadow = new ImgGraphicsLayer();
	public ImgGraphicsLayer glActiveThickness = new ImgGraphicsLayer();
	public ImgGraphicsLayer glActiveDominoes = new ImgGraphicsLayer();
	public ImgGraphicsLayer glActiveOverlays = new ImgGraphicsLayer();
	
	public ImgGraphicsLayer glParticles = new ImgGraphicsLayer();
	
	public LayerPanel layers[] = {
		glBackground,
		glShadow,
		glThickness,
		glDominoes,
		glOverlays,
		glDropZones,
		glHandShadow,
		glHandThickness,
		glHandDominoes,
		glHandOverlays,
		glHUD,
		glActiveShadow,
		glActiveThickness,
		glActiveDominoes,
		glActiveOverlays,
		glParticles
	};
	
	
	
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
		
		for (int i = 0; i < layers.length; i++) {
			add(layers[i], new Integer(i));
		}
		
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
