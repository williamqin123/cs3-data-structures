import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.*;

public class Game {
	
	private Domino spinner = null;
	
	private boolean occupied[][] = new boolean[1000 / 25][800 / 25];
	
	private int turn = -1;

	private List<Domino> dominoes = new ArrayList<Domino>();
	private List<Domino> placedDominoes = new ArrayList<Domino>();
	
	private List<Player> players = new ArrayList<Player>();
	
	private GameViewport g;
	
	private GameElement stickyElement = null;
	
	private DominoDropZone mouseOveredDropZone;
	
	
	
	private LinkedList<Domino> northBranch = new LinkedList<Domino>();
	private LinkedList<Domino> eastBranch = new LinkedList<Domino>();
	private LinkedList<Domino> southBranch = new LinkedList<Domino>();
	private LinkedList<Domino> westBranch = new LinkedList<Domino>();
	
	private LinkedList[] branches = {northBranch, eastBranch, southBranch, westBranch};
	
	

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
	
	public static Dimension negVect(Dimension v) {
		return new Dimension(-v.width, -v.height);
	}
	
	public static Dimension absVect(Dimension v) {
		return new Dimension(Math.abs(v.width), Math.abs(v.height));
	}
	
	public static Dimension flipVect(Dimension v) {
		return new Dimension(v.height, v.width);
	}
	
	
	public void putDroppableRects() {
		
		g.glDropZones.clearRenderList();
		clearBoardClickables();
		
		
		
		if (placedDominoes.size() == 0) {
			if (stickyElement.getRotation() % 180 != 0) new DominoDropZone(700, 425, null, GameElement.DIRECTION_NORTH);
		}
		else {
			for (LinkedList<Domino> branch : branches) {
				Domino tail = branch.getLast();
				
				int x = 0;
				int y = 0;
				int direction = 0;
				
				if (branch.size() > 1) {
					Domino prev = branch.get(branch.size() - 2);
					
					if (tail.getBoundsCenterX() == prev.getBoundsCenterX()) {
						x = tail.getBoundsCenterX();
						if (tail.getBoundsCenterY() > prev.getBoundsCenterY()) {
							y = tail.getBoundsCenterY() + 75 + Domino.DOMINO_DEPTH;
							direction = GameElement.DIRECTION_SOUTH;
						}
						else {
							y = tail.getBoundsCenterY() - 75 + Domino.DOMINO_DEPTH;
							direction = GameElement.DIRECTION_NORTH;
						}
					}
					else if (tail.getBoundsCenterY() == prev.getBoundsCenterY()) {
						y = tail.getBoundsCenterY() + Domino.DOMINO_DEPTH;
						if (tail.getBoundsCenterX() > prev.getBoundsCenterX()) {
							x = tail.getBoundsCenterX() + 75;
							direction = GameElement.DIRECTION_EAST;
						}
						else {
							x = tail.getBoundsCenterX() - 75;
							direction = GameElement.DIRECTION_WEST;
						}
					}
					
					else if (tail.getBoundsCenterY() != prev.getBoundsCenterY() && Math.abs(tail.getBoundsCenterY() - prev.getBoundsCenterY()) <= 25) {
						x = tail.getBoundsCenterX();
						if (tail.getBoundsCenterY() > prev.getBoundsCenterY()) {
							y = tail.getBoundsCenterY() + 75 + Domino.DOMINO_DEPTH;
							direction = GameElement.DIRECTION_SOUTH;
						}
						else {
							y = tail.getBoundsCenterY() - 75 + Domino.DOMINO_DEPTH;
							direction = GameElement.DIRECTION_NORTH;
						}
					}
					else if (tail.getBoundsCenterX() != prev.getBoundsCenterX() && Math.abs(tail.getBoundsCenterX() - prev.getBoundsCenterX()) <= 25) {
						y = tail.getBoundsCenterY() + Domino.DOMINO_DEPTH;
						if (tail.getBoundsCenterX() > prev.getBoundsCenterX()) {
							x = tail.getBoundsCenterX() + 75;
							direction = GameElement.DIRECTION_EAST;
						}
						else {
							x = tail.getBoundsCenterX() - 75;
							direction = GameElement.DIRECTION_WEST;
						}
					}
				}
				else {
					if (branch == northBranch && eastBranch.size() > 1 && westBranch.size() > 1) {
						x = 700;
						y = 325;
						direction = GameElement.DIRECTION_NORTH;
					}
					else if (branch == eastBranch) {
						x = 750;
						y = 400;
						direction = GameElement.DIRECTION_EAST;
					}
					else if (branch == southBranch && eastBranch.size() > 1 && westBranch.size() > 1) {
						x = 700;
						y = 475;
						direction = GameElement.DIRECTION_SOUTH;
					}
					else if (branch == westBranch) {
						x = 650;
						y = 400;
						direction = GameElement.DIRECTION_WEST;
					}
					else {
						continue;
					}
				}
				
				tail.openTilePips = tail.getHalfPips(direction);
				
				/*
				int pipsToMatch = ((Domino)stickyElement).getHalfPips(direction + 180);
				
				if (direction % 180 == (int)(stickyElement.getRotation()) % 180) {
					
					System.out.println(direction % 180);
					System.out.println((int)(stickyElement.getRotation()) % 180);
					
					if (tail.getRotation() % 180 == stickyElement.getRotation() % 180) {
						if (tail.getHalfPips(direction) != pipsToMatch) {
							continue;
						}
					}
					else if (tail.type == Domino.PERPENDICULAR) {
					
						if (tail.getPips()[0] != pipsToMatch && tail.getPips()[1] != pipsToMatch) {
							continue;
						}
					}
				}
				else {
					continue;
				}
				*/
				
				Domino floating = ((Domino)stickyElement);
				
				if (tail.type == Domino.INLINE) {
					if (floating.getHalfPips(GameElement.DIRECTION_SOUTH) == tail.openTilePips && direction != GameElement.DIRECTION_SOUTH) {
						direction = GameElement.DIRECTION_NORTH;
					}
					else if (floating.getHalfPips(GameElement.DIRECTION_WEST) == tail.openTilePips && direction != GameElement.DIRECTION_WEST) {
						direction = GameElement.DIRECTION_EAST;
					}
					else if (floating.getHalfPips(GameElement.DIRECTION_NORTH) == tail.openTilePips && direction != GameElement.DIRECTION_NORTH) {
						direction = GameElement.DIRECTION_SOUTH;
					}
					else if (floating.getHalfPips(GameElement.DIRECTION_EAST) == tail.openTilePips && direction != GameElement.DIRECTION_EAST) {
						direction = GameElement.DIRECTION_WEST;
					}
					else {
						continue;
					}
				}
				else if (tail.type == Domino.PERPENDICULAR) {
					if (!(floating.getHalfPips(direction + 180) == tail.getPips()[0] || floating.getHalfPips(direction + 180) == tail.getPips()[1])) {
						continue;
					}
				}
				
				int dx = x;
				int dy = y;
				
				switch (direction) {
				case 270:
					dy -= 50; break;
				case 0:
					dx += 50; break;
				case 90:
					dy += 50; break;
				case 180:
					dx -= 50; break;
				}
				
				if (occupied[(x - 200) / 25][y / 25] || occupied[(x - 200) / 25 - 1][y / 25] || occupied[(x - 200) / 25][y / 25 - 1] || occupied[(x - 200) / 25 - 1][y / 25 - 1]
				||  occupied[(dx - 200) / 25][dy / 25] || occupied[(dx - 200) / 25 - 1][dy / 25] || occupied[(dx - 200) / 25][dy / 25 - 1] || occupied[(dx - 200) / 25 - 1][dy / 25 - 1]) {
					
					continue;
				}
				
				new DominoDropZone(x, y, branch, direction);
			}
		}
		
		
		
		for (GameElement zone : g.glDropZones.getObjects()) {
			ClickArea z = new ClickArea(zone.getLeftBound(), zone.getTopBound(), zone.getRightBound(), zone.getBottomBound());
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
	
	public void clearBoardClickables() {
		for (Iterator<ClickArea> iterator = g.getClickZones().iterator(); iterator.hasNext(); ) {
			ClickArea a = iterator.next();
		    if (a.getName().equals("place-domino")) {
		    	if (a.getTarget() != null) a.getTarget().setClickArea(null);
				iterator.remove();
			}
		}
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
		
		else if (zone.getName().equals("place-domino")) {
			
			// Put domino
			
			Player ap = getActivePlayer();

			ap.hand.remove(stickyElement);
			
			int x = el.getBoundsCenterX();
			int y = el.getBoundsCenterY();
			
			stickyElement.centerX(x);
			stickyElement.centerY(y);
			stickyElement.setDY(-5);
			stickyElement.setLayer(g.Z_INDEX_BASE);
			placedDominoes.add((Domino) stickyElement);
			g.glDropZones.clearRenderList();
			
			occupied[(x - 200) / 25][y / 25] = true;
			occupied[(x - 200) / 25 - 1][y / 25] = true;	
			occupied[(x - 200) / 25][y / 25 - 1] = true;
			occupied[(x - 200) / 25 - 1][y / 25 - 1] = true;
			
			if (stickyElement.getRotation() % 180 == 0) {
				occupied[(x - 200) / 25 + 1][y / 25] = true;
				occupied[(x - 200) / 25 - 2][y / 25] = true;	
				occupied[(x - 200) / 25 + 1][y / 25 - 1] = true;
				occupied[(x - 200) / 25 - 2][y / 25 - 1] = true;
			} else {
				occupied[(x - 200) / 25][y / 25 + 1] = true;
				occupied[(x - 200) / 25 - 1][y / 25 + 1] = true;	
				occupied[(x - 200) / 25][y / 25 - 2] = true;
				occupied[(x - 200) / 25 - 1][y / 25 - 2] = true;
			}
			
			
			if (placedDominoes.size() == 1) {
				((Domino)stickyElement).type = Domino.PERPENDICULAR;
				for (LinkedList<Domino> branch : branches) {
					branch.add((Domino) stickyElement);
				}
			}
			else {
				((DominoDropZone) el).branch.add((Domino) stickyElement);
				//ap.addPoints(((DominoDropZone) el).branch.getLast().openTilePips);
			}
			
			stickyElement = null;
			
			nextTurn();
		}
		
		else if (el instanceof Domino && getActivePlayer().hand.contains(el)) {
			
			//System.out.println("Domino pressed");
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
			
			putDroppableRects();
			
			repaintActiveLayers();
		}
	}
	
	public void handleCursorMoveEvent(int x, int y) {
		if (stickyElement == null) return;
		
		stickyElement.centerX(x);
		stickyElement.centerY(y);
		
		ClickArea hover = g.click((int)(x * g.getScale()), (int)(y * g.getScale()));
		
		if (hover != null && (hover.getTarget() instanceof DominoDropZone)) {
			hover.getTarget().setImage("blue-rect-hover.png");
			mouseOveredDropZone = (DominoDropZone) hover.getTarget();
			stickyElement.centerX(hover.getTarget().getBoundsCenterX());
			stickyElement.centerY(hover.getTarget().getBoundsCenterY());
		}
		else if (mouseOveredDropZone != null) {
			mouseOveredDropZone.setImage("blue-rect.png");
			mouseOveredDropZone = null;
		}
		
		repaintActiveLayers();
	}
	
}
