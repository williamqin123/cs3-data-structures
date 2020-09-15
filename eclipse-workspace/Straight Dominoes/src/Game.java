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
		
		for (int i = 0; i <= count; i++) {
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
		
		for (Domino dom : getActivePlayer().hand) {
			
			dom.centerX(100);
			dom.centerY((int)((i + 1.0) / 8.0 * Window.DEFAULT_HEIGHT));
			
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
	}
	
	public void start() {
		
		GameElement board = new GameElement(StraightDominoesApp.storage.get("board.png"));
		board.setWidth(Window.DEFAULT_WIDTH);
		board.setHeight(Window.DEFAULT_HEIGHT);
		
		g.glBackground.addDrawable(board);
		
		g.glBackground.repaint();
		
		makeDominoes();
		
		shuffleDominoes(dominoes);
		
		makePlayers(4);
		
		distributeDominoes(dominoes, players, 7);
		
		nextTurn();
		
	}
	
	public void handleClickEvent(GameElement el) {
		if (el instanceof Domino && getActivePlayer().hand.contains(el)) {
			stickyElement = el;
			el.setDY(-20);
			
			g.repaint();
		}
	}
	
	public void handleCursorMoveEvent(int x, int y) {
		if (stickyElement == null) return;
		
		stickyElement.centerX(x);
		stickyElement.centerY(y);
		
		g.repaint();
	}
	
}
