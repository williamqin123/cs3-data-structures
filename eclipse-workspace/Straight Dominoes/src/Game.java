import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.*;

public class Game {
	
	private Domino spinner = null;
	
	public static int[] playingBoardXBounds = {
		200, 700, 1200 // left, middle, right
	};
	public static int[] playingBoardYBounds = {
		0, 400, 800 // top, middle, bottom
	};
	
	private boolean occupied[][] = new boolean[(playingBoardXBounds[2] - playingBoardXBounds[0]) / 25][(playingBoardYBounds[2] - playingBoardXBounds[0]) / 25];
	
	private int playerCount = 4;
	
	private int turn = (int)(Math.random() * playerCount);

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

	private CenterAlignedTextLabel topMessage;
	
	

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
				if (n1 == n2) {
					d.type = Domino.PERPENDICULAR;
				}
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
		
		Player previousPlayer = players.get(turn);
		((TextLabel) previousPlayer.linkedObjects.get("nametag")).removeFlag("scoreboard-active");
		((TextLabel) previousPlayer.linkedObjects.get("round-points")).removeFlag("scoreboard-active");
		((TextLabel) previousPlayer.linkedObjects.get("total-points")).removeFlag("scoreboard-active");
		
		turn = (turn + 1) % players.size();
		showHand(turn);
		
		Player currentPlayer = players.get(turn);
		((TextLabel) currentPlayer.linkedObjects.get("nametag")).addFlag("scoreboard-active");
		((TextLabel) currentPlayer.linkedObjects.get("round-points")).addFlag("scoreboard-active");
		((TextLabel) currentPlayer.linkedObjects.get("total-points")).addFlag("scoreboard-active");
		
		if (placedDominoes.size() == 0) {
			topMessage.setText("Player " + (turn + 1) + " goes first. Click a domino to pick it up, right-click to rotate 90º.");
		}
		else {
			topMessage.setText("");
		}
		
		 updateDisplayedScores();
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
		

		
		Domino floating = ((Domino)stickyElement);
		
		if (placedDominoes.size() == 0) {
			if (floating.getAxisOrientation() == Domino.VERTICAL_AXIS) new DominoDropZone(playingBoardXBounds[1], playingBoardYBounds[1] + 25, null, GameElement.DIRECTION_NORTH);
		}
		else {
			for (LinkedList<Domino> branch : branches) {
				Domino tail = branch.getLast();
				
				int x = 0;
				int y = 0;
				int direction = 0;

				int leadingXCenter;
				int leadingYCenter;
				
				if (branch.size() > 1) {
					leadingXCenter = branch.get(branch.size() - 2).getBoundsCenterX();
					leadingYCenter = branch.get(branch.size() - 2).getBoundsCenterY();
				}
				else {
					if (branch == northBranch && eastBranch.size() > 1 && westBranch.size() > 1) {
						leadingXCenter = playingBoardXBounds[1];
						leadingYCenter = (int)Double.POSITIVE_INFINITY;
					}
					else if (branch == eastBranch) {
						leadingXCenter = (int)Double.NEGATIVE_INFINITY;
						leadingYCenter = playingBoardYBounds[1] - Domino.DOMINO_DEPTH;
					}
					else if (branch == southBranch && eastBranch.size() > 1 && westBranch.size() > 1) {
						leadingXCenter = playingBoardXBounds[1];
						leadingYCenter = (int)Double.NEGATIVE_INFINITY;
					}
					else if (branch == westBranch) {
						leadingXCenter = (int)Double.POSITIVE_INFINITY;
						leadingYCenter = playingBoardYBounds[1] - Domino.DOMINO_DEPTH;
					}
					else {
						continue;
					}
				}
					
				if (tail.getBoundsCenterX() == leadingXCenter) {
					x = tail.getBoundsCenterX();
					if (tail.getBoundsCenterY() > leadingYCenter) {
						y = tail.getBoundsCenterY() + 75 + Domino.DOMINO_DEPTH;
						direction = GameElement.DIRECTION_SOUTH;
					}
					else {
						y = tail.getBoundsCenterY() - 75 + Domino.DOMINO_DEPTH;
						direction = GameElement.DIRECTION_NORTH;
					}
				}
				else if (tail.getBoundsCenterY() == leadingYCenter) {
					y = tail.getBoundsCenterY() + Domino.DOMINO_DEPTH;
					if (tail.getBoundsCenterX() > leadingXCenter) {
						x = tail.getBoundsCenterX() + 75;
						direction = GameElement.DIRECTION_EAST;
					}
					else {
						x = tail.getBoundsCenterX() - 75;
						direction = GameElement.DIRECTION_WEST;
					}
				}
				
				else if (tail.getBoundsCenterY() != leadingYCenter && Math.abs(tail.getBoundsCenterY() - leadingYCenter) <= 25) {
					x = tail.getBoundsCenterX();
					if (tail.getBoundsCenterY() > leadingYCenter) {
						y = tail.getBoundsCenterY() + 75 + Domino.DOMINO_DEPTH;
						direction = GameElement.DIRECTION_SOUTH;
					}
					else {
						y = tail.getBoundsCenterY() - 75 + Domino.DOMINO_DEPTH;
						direction = GameElement.DIRECTION_NORTH;
					}
				}
				else if (tail.getBoundsCenterX() != leadingXCenter && Math.abs(tail.getBoundsCenterX() - leadingXCenter) <= 25) {
					y = tail.getBoundsCenterY() + Domino.DOMINO_DEPTH;
					if (tail.getBoundsCenterX() > leadingXCenter) {
						x = tail.getBoundsCenterX() + 75;
						direction = GameElement.DIRECTION_EAST;
					}
					else {
						x = tail.getBoundsCenterX() - 75;
						direction = GameElement.DIRECTION_WEST;
					}
				}
				
				tail.openTilePips = tail.getHalfPips(direction);
				
				x -= playingBoardXBounds[0];
				y -= playingBoardYBounds[0];
				
				if (tail.type == Domino.INLINE) {
					if (floating.type == Domino.INLINE) {
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
					else if (floating.type == Domino.PERPENDICULAR) {
						if (floating.getHalfPips(GameElement.DIRECTION_NORTH) == floating.getHalfPips(GameElement.DIRECTION_SOUTH)
						&&  floating.getHalfPips(GameElement.DIRECTION_NORTH) == tail.openTilePips
						&&  (direction == GameElement.DIRECTION_EAST || direction == GameElement.DIRECTION_WEST)) {
							direction = GameElement.DIRECTION_SOUTH;
							y -= 25;
							//System.out.println("y");
						}
						else if (floating.getHalfPips(GameElement.DIRECTION_EAST) == floating.getHalfPips(GameElement.DIRECTION_WEST)
						&&  floating.getHalfPips(GameElement.DIRECTION_EAST) == tail.openTilePips
						&&  (direction == GameElement.DIRECTION_NORTH || direction == GameElement.DIRECTION_SOUTH)) {
							direction = GameElement.DIRECTION_EAST;
							x -= 25;
							//System.out.println("x");
						}
						else {
							continue;
						}
					}
				}
				else if (tail.type == Domino.PERPENDICULAR) {
					
					if (floating.type == Domino.PERPENDICULAR) continue; // no doubles to doubles or doubles off the spinner
					
					int backEndPips = floating.getHalfPips(direction + 180);
					
					if (backEndPips == -1) continue; // a perpendicular must be followed by an inline
					
					if ((direction == Domino.DIRECTION_EAST || direction == Domino.DIRECTION_WEST)
					&& (backEndPips == tail.getHalfPips(Domino.DIRECTION_NORTH) || backEndPips == tail.getHalfPips(Domino.DIRECTION_SOUTH))) {}
					
					else if ((direction == Domino.DIRECTION_NORTH || direction == Domino.DIRECTION_SOUTH)
					&& (backEndPips == tail.getHalfPips(Domino.DIRECTION_EAST) || backEndPips == tail.getHalfPips(Domino.DIRECTION_WEST))) {}
					/*
					else if (direction == Domino.DIRECTION_NORTH && backEndPips == tail.getHalfPips(Domino.DIRECTION_NORTH)) {}
					else if (direction == Domino.DIRECTION_SOUTH && backEndPips == tail.getHalfPips(Domino.DIRECTION_SOUTH)) {}
					else if (direction == Domino.DIRECTION_EAST && backEndPips == tail.getHalfPips(Domino.DIRECTION_EAST)) {}
					else if (direction == Domino.DIRECTION_WEST && backEndPips == tail.getHalfPips(Domino.DIRECTION_WEST)) {}
					*/
					else if (backEndPips == tail.getHalfPips(direction)) {}
					else {
						continue;
					}
					
					if (floating.getAxisOrientation() != tail.getAxisOrientation()) {
						switch (direction) {
						case 270:
							y += 25; break;
						case 0:
							x -= 25; break;
						case 90:
							y -= 25; break;
						case 180:
							x += 25; break;
						}
					}
				}
				/*else {
					if (floating.getHalfPips(Domino.DIRECTION_SOUTH) != 0
					&&  floating.getHalfPips(Domino.DIRECTION_SOUTH) == tail.getHalfPips(Domino.DIRECTION_NORTH)) {
						continue;
					}
				}*/
				
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
				
				//System.out.println(dy);
				
				try {
					if (occupied[x / 25][y / 25] || occupied[x / 25 - 1][y / 25] || occupied[x / 25][y / 25 - 1] || occupied[x / 25 - 1][y / 25 - 1]
					||  occupied[dx / 25][dy / 25] || occupied[dx / 25 - 1][dy / 25] || occupied[dx / 25][dy / 25 - 1] || occupied[dx / 25 - 1][dy / 25 - 1]) {
						
						continue;
					}
				}
				catch (IndexOutOfBoundsException e) {
					continue;
				}
				
				new DominoDropZone(x + playingBoardXBounds[0], y + playingBoardYBounds[0], branch, direction);
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
		
		makePlayers(playerCount);
		
		distributeDominoes(dominoes, players, 7);
		
		setupScorecard();
		
		ClickArea passButton = new ClickArea(1117, 0, 1200, 50);
		passButton.setName("pass");
		g.addClickZone(passButton);
		
		topMessage = new CenterAlignedTextLabel("", "Tahoma", Font.PLAIN, 16, Color.WHITE, 600, 43);
		g.glHUD.addLabel(topMessage);
		
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
			
			topMessage.setText("");
			g.glHUD.repaint();
			
			showHand(turn);
		}
		
		else if (zone.getName().equals("pass") && stickyElement == null) {
			if (true/*playerHasAvailableMoves()*/) {
				nextTurn();
			}
			else {
				topMessage.setText("You can't pass because you have possible move(s).");
			}
		}
		
		else if (zone.getName().equals("place-domino")) {
			
			// Put domino
			
			Player ap = getActivePlayer();

			ap.hand.remove(stickyElement);
			
			Domino dom = (Domino)stickyElement;
			
			int x = el.getBoundsCenterX();
			int y = el.getBoundsCenterY();
			
			stickyElement.centerX(x);
			stickyElement.centerY(y);
			stickyElement.setDY(-5);
			stickyElement.setLayer(g.Z_INDEX_BASE);
			placedDominoes.add(dom);
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
				dom.type = Domino.PERPENDICULAR;
				for (LinkedList<Domino> branch : branches) {
					branch.add(dom);
				}
				int pips = dom.getTotalPipCount();
				
				if (pips % 5 == 0) {
					ap.addPoints(pips);
				}
				spinner = dom;
			}
			else {
				
				((DominoDropZone) el).branch.add(dom);
				
				dom.openTilePips = dom.getHalfPips(el.getRotation());
				
				int sumOfExposedEndPips = 0;
				int startedBranches = 0;
				for (LinkedList<Domino> branch : branches) {
					Domino end = branch.getLast();
					if (branch.size() >= 2) {
						if (end.type == Domino.PERPENDICULAR) {
							sumOfExposedEndPips += end.getTotalPipCount();
						} else {
							sumOfExposedEndPips += end.openTilePips;
						}
						startedBranches++;
					}
				}
				if (startedBranches < 2) {
					sumOfExposedEndPips += spinner.getTotalPipCount();
				}
				if (sumOfExposedEndPips % 5 == 0) {
					ap.addPoints(sumOfExposedEndPips);
				}
			}
			
			stickyElement = null;
			
			nextTurn();
		}
		
		else if (el instanceof Domino && getActivePlayer().hand.contains(el)) {
			
			// removes instructional message when first player clicks on a domino
			if (topMessage.getText().contains("goes first")) {
				topMessage.setText("");
			}
			
			//System.out.println("Domino pressed");
			clearDominoClickables();
			stickyElement = el;
			el.setDY(-20);
			
			if (placedDominoes.size() == 0) {
				el.rotate(90);
			}
			else if (placedDominoes.size() == 1) {
				if (((Domino)el).type == Domino.PERPENDICULAR) {
					topMessage.setText("You can't place a double straight off the spinner.");
				}
			}
			
			// transfers selected domino from hand layer to active layer
			el.setLayer(StraightDominoesApp.window.viewport.Z_INDEX_ACTIVE);
			
			putDroppableRects();
			
			repaintHandLayers();
			repaintActiveLayers();
			
			
			
			ClickArea leftSidebar = new ClickArea(0, 0, 210, 800);
			leftSidebar.setName("hand");
			g.addClickZone(leftSidebar);
			
			g.glHUD.repaint();
		}
	}
	
	/*private boolean playerHasAvailableMoves() {
		for (Domino d : getActivePlayer().hand) {
			
		}
	}*/

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
