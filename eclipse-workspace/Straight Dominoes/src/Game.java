import java.awt.Color;
import java.awt.Font;
import java.util.*;

public class Game {
	
	private int turn = -1;

	private List<Domino> dominoes = new ArrayList<Domino>();
	
	private List<Player> players = new ArrayList<Player>();
	
	private GameViewport g;
	
	private GameElement stickyElement = null;
	
	

	public Game(GameViewport gl) {
		g = gl;
	}
	
	private void repaintActiveLayers() {
		g.glActiveShadow.repaint();
		g.glActiveDominoes.repaint();
	}
	
	
	
	private Player getActivePlayer() {
		return players.get(turn);
	}
	
	private void makeDominoes() {
		final int DOMINO_MAX_PIPS = 6;
		
		for (int n1 = 0; n1 <= DOMINO_MAX_PIPS; n1++) {
			for (int n2 = 0; n2 <= n1; n2++) {
				Domino d = new Domino(n1, n2);
				dominoes.add(d);
			}
		}
	}
	
	private void shuffleDominoes(List<Domino> dominoes) {
		Collections.shuffle(dominoes);
	}
	
	private void makePlayers(int count) {
		
		for (int i = 0; i < count; i++) {
			players.add(new Player());
		}
	}
	
	private void distributeDominoes(List<Domino> dominoes, List<Player> players, int dominoesPerPlayer) {
		
		int i = 0;
		int accumulator = 0;
		
		for (Domino dom : dominoes) {
			players.get(i).hand.add(dom);
			
			accumulator++;
			if (accumulator >= dominoesPerPlayer) {
				accumulator = 0;
				i++;
				if (i >= players.size()) break;
			}
		}
	}
	
	private void showHand(int playerIndex) {
		g.glHandDominoes.clearRenderList();
		g.glHandShadow.clearRenderList();
		
		int i = 0;
		
		ArrayList<Domino> hand = getActivePlayer().hand;
		int handSize = hand.size();
		for (Domino dom : hand) {
			
			dom.centerX(100);
			dom.centerY((int)(290 + (i + 1.0) / (handSize + 1.0) * 510));
			
			g.addClickZone(dom.getX(), dom.getY(), dom.getX() + dom.getWidth(), dom.getY() + dom.getHeight(), dom);
			
			g.glHandDominoes.addDrawable(dom);
			
			i++;
		}
		
		g.glHandDominoes.repaint();
		g.glHandShadow.repaint();
	}
	
	private void nextTurn() {
		turn = (turn + 1) % players.size();
		showHand(turn);
		
		TextLabel currentPlayerLabel = players.get(turn).getScoreLabel();
		currentPlayerLabel.addFlag("scoreboard-active");
		
		g.glHUD.repaint();
	}
	
	private void updateDisplayedScores() {
		
		for (Player player : players) {
			TextLabel t = player.getScoreLabel();
			t.setText(t.getText().substring(0, t.getText().indexOf(':') + 2) + player.getScore());
		}
		
		g.glHUD.repaint();
	}
	
	private void setupScorecard() {
		
		g.glHUD.addLabel(new TextLabel("Round 1", "Tahoma", Font.BOLD, 25, Color.BLACK, 30, 100));
		
		for (int i = 1; i <= players.size(); i++) {
			
			TextLabel t = new TextLabel("Player " + i + ": 0", "Tahoma", Font.PLAIN, 20, Color.BLACK, 30, 100 + 40 * i);
		
			g.glHUD.addLabel(t);
			
			players.get(i - 1).linkScoreLabel(t);
		
		}
		
		g.glHUD.repaint();
	}
	
	public void start() {
		
		GameElement board = new GameElement(StraightDominoesApp.storage.get("new-bg.png"));
		board.setWidth(Window.DEFAULT_WIDTH);
		board.setHeight(Window.DEFAULT_HEIGHT);
		
		g.glBackground.addDrawable(board);
		
		g.glBackground.repaint();
		
		makeDominoes();
		
		shuffleDominoes(dominoes);
		
		makePlayers(4);
		
		distributeDominoes(dominoes, players, 7);
		
		setupScorecard();
		
		nextTurn();
		
	}
	
	public void handleClickEvent() {};
	
	public void handleClickEvent(GameElement el, ClickArea zone) {
		
		if (zone.getName().equals("hand")) {
			g.removeClickZone(zone);
			
			stickyElement.setDY(0);
			g.glActiveDominoes.removeDrawable(el);
			stickyElement = null;
			showHand(turn);
		}
		
		else if (el instanceof Domino && getActivePlayer().hand.contains(el)) {
			g.removeClickZone(zone);
			stickyElement = el;
			el.setDY(-20);
			
			// transfers selected domino from hand layer to active layer
			g.glHandDominoes.removeDrawable(el);
			g.glActiveDominoes.addDrawable(el);
			
			g.glHandShadow.repaint();
			g.glHandDominoes.repaint();
			
			repaintActiveLayers();
			
			
			
			ClickArea leftSidebar = new ClickArea(0, 0, 210, 800);
			leftSidebar.setName("hand");
			g.addClickZone(leftSidebar);
		}
	}
	
	public void handleRightClickEvent(GameElement el, ClickArea zone) {}
	
	public void handleRightClickEvent() {
		if (stickyElement instanceof Domino) {
			
			stickyElement.rotate(90);
			
			repaintActiveLayers();
		}
	}
	
	public void handleCursorMoveEvent(int x, int y) {
		if (stickyElement == null) return;
		
		stickyElement.centerX(x);
		stickyElement.centerY(y);
		
		repaintActiveLayers();
	}
	
}
