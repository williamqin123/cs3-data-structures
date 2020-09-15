
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GameViewport extends JLayeredPane implements MouseListener, MouseMotionListener {
	
	public ImgGraphicsLayer glBackground = new ImgGraphicsLayer();
	public ImgGraphicsLayer glShadow = new ImgGraphicsLayer();
	public ImgGraphicsLayer glDominoes = new ImgGraphicsLayer();
	public ImgGraphicsLayer glDropZones = new ImgGraphicsLayer();
	public ImgGraphicsLayer glHandShadow = new ImgGraphicsLayer();
	public ImgGraphicsLayer glHandDominoes = new ImgGraphicsLayer();
	public ImgGraphicsLayer glActiveShadow = new ImgGraphicsLayer();
	public ImgGraphicsLayer glActiveDominoes = new ImgGraphicsLayer();
	//public ImgGraphicsLayer glHUD = new TextGraphicsLayer();
	public ImgGraphicsLayer glParticles = new ImgGraphicsLayer();
	
	
	private List<ClickArea> clickZones = new ArrayList<ClickArea>();
	
	public void addClickZone(int x0, int y0, int x1, int y1, GameElement target) {
		ClickArea region = new ClickArea(x0, y0, x1, y1);
		region.setTarget(target);
		clickZones.add(region);
	}
	public void removeClickZone(ClickArea zone) {
		clickZones.remove(zone);
	}
	public ClickArea click(int x, int y) {
		for (ClickArea zone : clickZones) {
			if (x >= zone.x0 && x <= zone.x1
			&&  y >= zone.y0 && y <= zone.y1) {
				return zone;
			}
		}
		return null;
	}
	
	
	
	public GameViewport(){
		
		super();
		
		setPreferredSize(new Dimension(Window.DEFAULT_WIDTH, Window.DEFAULT_HEIGHT));
		
		add(glBackground, new Integer(0));
		add(glShadow, new Integer(1));
		add(glDominoes, new Integer(2));
		add(glDropZones, new Integer(3));
		add(glHandShadow, new Integer(4));
		add(glHandDominoes, new Integer(5));
		add(glActiveShadow, new Integer(6));
		add(glActiveDominoes, new Integer(7));
		add(glParticles, new Integer(9));
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		
		ClickArea zone = click(e.getX(), e.getY());
		
		if (zone == null) return;
		
		StraightDominoesApp.game.handleClickEvent(zone.getTarget());
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
		StraightDominoesApp.game.handleCursorMoveEvent(e.getX(), e.getY());
		
	}

}
