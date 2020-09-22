import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.*;

public class Game {
	
	private int turn = -1;

	private List<Domino> dominoes = new ArrayList<Domino>();
	
	private List<Domino> placedDominoes = new ArrayList<Domino>();
	
	private List<Player> players = new ArrayList<Player>();
	
	private GameViewport g;
	
	private GameElement stickyElement = null;
	
	private DominoDropZone mouseOveredDropZone;
	
	private Dimension northBranchTailVector = new Dimension(0, -4);
	private Dimension eastBranchTailVector = new Dimension(3, 0);
	private Dimension southBranchTailVector = new Dimension(0, 4);
	private Dimension westBranchTailVector = new Dimension(-3, 0);
	private Domino lastPlayedNorthBranchDomino;
	private Domino lastPlayedEastBranchDomino;
	private Domino lastPlayedSouthBranchDomino;
	private Domino lastPlayedWestBranchDomino;

	public Game(GameViewport gl) {
		g = gl;
	}
	
	private void repaintActiveLayers() {
		g.glActiveShadow.repaint();
		g.glActiveThickness.repaint();
		g.glActiveDominoes.repaint();
		g.glActiveOverlays.repaint();
	}
	private void repaintHandLayers() {
		g.glHandShadow.repaint();
		g.glHandThickness.repaint();
		g.glHandDominoes.repaint();
		g.glHandOverlays.repaint();
	}
	private void repaintBaseLayers() {
		g.glShadow.repaint();
		g.glThickness.repaint();
		g.glDominoes.repaint();
		g.glOverlays.repaint();
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
		
		g.glHandShadow.clearRenderList();
		g.glHandThickness.clearRenderList();
		g.glHandDominoes.clearRenderList();
		g.glHandOverlays.clearRenderList();
		
		int i = 0;
		
		ArrayList<Domino> hand = getActivePlayer().hand;
		int handSize = hand.size();
		for (Domino dom : hand) {
			
			dom.centerX(100);
			dom.centerY((int)(285 + (i + 1.0) / (handSize + 1.0) * 510));
			
			g.addClickZone(dom.getX(), dom.getY(), dom.getX() + dom.getWidth(), dom.getY() + dom.getHeight(), dom);
			
			dom.setLayer(StraightDominoesApp.window.viewport.Z_INDEX_HAND);
			
			i++;
		}
		
		repaintHandLayers();
	}
	
	private void nextTurn() {
		
		clearDominoClickables();
		
		Player previousPlayer = players.get(Math.max(turn, 0));
		((TextLabel) previousPlayer.linkedObjects.get("nametag")).removeFlag("scoreboard-active");
		((TextLabel) previousPlayer.linkedObjects.get("round-points")).removeFlag("scoreboard-active");
		((TextLabel) previousPlayer.linkedObjects.get("total-points")).removeFlag("scoreboard-active");
		
		turn = (turn + 1) % players.size();
		showHand(turn);
		
		Player currentPlayer = players.get(turn);
		((TextLabel) currentPlayer.linkedObjects.get("nametag")).addFlag("scoreboard-active");
		((TextLabel) currentPlayer.linkedObjects.get("round-points")).addFlag("scoreboard-active");
		((TextLabel) currentPlayer.linkedObjects.get("total-points")).addFlag("scoreboard-active");
		
		g.glHUD.repaint();
	}
	
	private void updateDisplayedScores() {
		
		for (Player player : players) {
			TextLabel roundPoints = (TextLabel) player.linkedObjects.get("round-points");
			TextLabel totalPoints = (TextLabel) player.linkedObjects.get("total-points");
			roundPoints.setText(Integer.toString(player.getCurrentRoundScore()));
			totalPoints.setText(Integer.toString(player.getTotalScore()));
		}
		
		g.glHUD.repaint();
	}
	
	private void setupScorecard() {
		
		g.glHUD.addLabel(new TextLabel("Round 1", "Verdana", Font.BOLD, 23, Color.BLACK, 20, 90));
		g.glHUD.addLabel(new TextLabel("Current:", "Arial Narrow", Font.PLAIN, 15, Color.BLACK, 109, 115));
		g.glHUD.addLabel(new TextLabel("Total:", "Arial Narrow", Font.PLAIN, 15, Color.BLACK, 164, 116));
		
		for (int i = 1; i <= players.size(); i++) {
			
			TextLabel playerName = new TextLabel("Player " + i, "Tahoma", Font.PLAIN, 17, Color.BLACK, 20 - i, 99 + 40 * i);
			TextLabel playerRoundScore = new TextLabel("0", "Tahoma", Font.PLAIN, 19, Color.BLACK, 110 - i, 101 + 40 * i);
			TextLabel playerGameScore = new TextLabel("0", "Tahoma", Font.BOLD, 19, Color.BLACK, 165 - i, 102 + 40 * i);

			g.glHUD.addLabel(playerName);
			g.glHUD.addLabel(playerRoundScore);
			g.glHUD.addLabel(playerGameScore);
			
			Player player = players.get(i - 1);
			
			player.linkedObjects.put("nametag", playerName);
			player.linkedObjects.put("round-points", playerRoundScore);
			player.linkedObjects.put("total-points", playerGameScore);
		
		}
		
		g.glHUD.repaint();
	}
	
	public static int[] cartesian(int x, int y) {
		return new int[]{x * 25 + 700, y * 25 + 400};
	}
	
	public static int[] antiCartesian(int x, int y) {
		return new int[]{(x - 700) / 25, (y - 400) / 25};
	}
	
	public void putDroppableRects() {
		
		if (placedDominoes.size() == 0) {
			DominoDropZone s = new DominoDropZone(0, 0, "spinner", new Dimension(0, 0));
			s.rotate(90);
		}
		else {
			if (lastPlayedEastBranchDomino == null) {
		
				DominoDropZone e = new DominoDropZone(eastBranchTailVector.width, eastBranchTailVector.height, "east", new Dimension(4, 0));
			}
			else {
				DominoDropZone eu = new DominoDropZone(antiCartesian(lastPlayedEastBranchDomino.getX() + lastPlayedEastBranchDomino.getHeight() / 2, 0)[0] + eastBranchTailVector.width, antiCartesian(0, lastPlayedEastBranchDomino.getY())[1] + eastBranchTailVector.height, "east", new Dimension(0, -4));
				DominoDropZone ed = new DominoDropZone(antiCartesian(lastPlayedEastBranchDomino.getX() + lastPlayedEastBranchDomino.getHeight() / 2, 0)[0] + eastBranchTailVector.width, antiCartesian(0, lastPlayedEastBranchDomino.getY() + lastPlayedEastBranchDomino.getWidth() / 2)[1] + eastBranchTailVector.height, "east", new Dimension(0, 4));
				DominoDropZone e = new DominoDropZone(antiCartesian(lastPlayedEastBranchDomino.getX() + lastPlayedEastBranchDomino.getWidth() / 2, 0)[0] + eastBranchTailVector.width, antiCartesian(0, lastPlayedEastBranchDomino.getY() + lastPlayedEastBranchDomino.getHeight() / 2)[1] + eastBranchTailVector.height, "east", new Dimension(4, 0));
				eu.rotate(90);
				ed.rotate(90);
			}
			if (lastPlayedWestBranchDomino == null) {

				DominoDropZone w = new DominoDropZone(westBranchTailVector.width, westBranchTailVector.height, "west", new Dimension(westBranchTailVector));
			}
		}
		
		for (GameElement zone : g.glDropZones.getObjects()) {
			ClickArea z;
			if (zone.getRotation() == 0) {
				z = new ClickArea(zone.getX() + zone.getDX(), zone.getY() + zone.getDY(), zone.getX() + zone.getWidth() + zone.getDX(), zone.getY() + zone.getHeight() + zone.getDY());
			}
			else {
				z = new ClickArea(zone.getX() + zone.getDY(), zone.getY() + zone.getDX(), zone.getX() + zone.getHeight() + zone.getDY(), zone.getY() + zone.getWidth() + zone.getDX());
			}
			z.setTarget(zone);
			z.setName("place-domino");
			g.addClickZone(z);
		}
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
		
		ClickArea passButton = new ClickArea(1117, 0, 1200, 50);
		passButton.setName("pass");
		g.addClickZone(passButton);
		
		nextTurn();
		
	}
	
	public void clearDominoClickables() {
		for (Iterator<ClickArea> iterator = g.getClickZones().iterator(); iterator.hasNext(); ) {
			ClickArea a = iterator.next();
		    if (a.getName().equals("hand") || a.getTarget() instanceof Domino) {
		    	if (a.getTarget() != null) a.getTarget().setClickArea(null);
				iterator.remove();
			}
		}
	}
	
	public void handleClickEvent() {};
	
	public void handleClickEvent(GameElement el, ClickArea zone) {
		
		if (zone.getName().equals("hand")) {
			g.removeClickZone(zone);
			
			stickyElement.setDY(-5);
			stickyElement.setRotation(0);
			stickyElement = null;
			
			g.glDropZones.clearRenderList();
			
			showHand(turn);
		}
		
		else if (zone.getName().equals("pass") && stickyElement == null) {
			nextTurn();
		}
		
		else if (zone.getName().equals("place-domino") && (stickyElement.getRotation() == el.getRotation() || stickyElement.getRotation() % 180 == el.getRotation())) {
			
			// Put domino

			getActivePlayer().hand.remove(stickyElement);
			stickyElement.centerX(el.getX());
			stickyElement.centerY(el.getY());
			stickyElement.setDY(-5);
			stickyElement.setLayer(g.Z_INDEX_BASE);
			placedDominoes.add((Domino) stickyElement);
			g.glDropZones.clearRenderList();
			
			String branch = ((DominoDropZone) el).getBranchName();
			
			switch (branch) {
			case "north":
				lastPlayedNorthBranchDomino = (Domino) stickyElement;
			case "east":
				lastPlayedEastBranchDomino = (Domino) stickyElement;
				eastBranchTailVector = ((DominoDropZone)zone.getTarget()).getVec();
			case "south":
				lastPlayedSouthBranchDomino = (Domino) stickyElement;
			case "west":
				lastPlayedWestBranchDomino = (Domino) stickyElement;
			}
			
			stickyElement = null;
			
			nextTurn();
		}
		
		else if (el instanceof Domino && getActivePlayer().hand.contains(el)) {
			
			System.out.println("Domino pressed");
			clearDominoClickables();
			stickyElement = el;
			el.setDY(-20);
			
			if (placedDominoes.size() == 0) el.rotate(90);
			
			// transfers selected domino from hand layer to active layer
			el.setLayer(StraightDominoesApp.window.viewport.Z_INDEX_ACTIVE);
			
			putDroppableRects();
			
			repaintHandLayers();
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
		
		ClickArea hover = g.click(x, y);
		
		if (hover != null && (hover.getTarget() instanceof DominoDropZone)) {
			hover.getTarget().setImage("blue-rect-hover.png");
			mouseOveredDropZone = (DominoDropZone) hover.getTarget();
		}
		else if (mouseOveredDropZone != null) {
			mouseOveredDropZone.setImage("blue-rect.png");
			mouseOveredDropZone = null;
		}
		
		repaintActiveLayers();
	}
	
}
