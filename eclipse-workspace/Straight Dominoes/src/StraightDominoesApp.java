
public class StraightDominoesApp {
	
	public static String GAME_TITLE = "Straight Dominoes Legends";
	
	private final static String[] filesList = {
		"src/resources/board.png",
			
		"src/resources/domino00.png",
		"src/resources/domino10.png",
		"src/resources/domino11.png",
		"src/resources/domino20.png",
		"src/resources/domino21.png",
		"src/resources/domino22.png",
		"src/resources/domino30.png",
		"src/resources/domino31.png",
		"src/resources/domino32.png",
		"src/resources/domino33.png",
		"src/resources/domino40.png",
		"src/resources/domino41.png",
		"src/resources/domino42.png",
		"src/resources/domino43.png",
		"src/resources/domino44.png",
		"src/resources/domino50.png",
		"src/resources/domino51.png",
		"src/resources/domino52.png",
		"src/resources/domino53.png",
		"src/resources/domino54.png",
		"src/resources/domino55.png",
		"src/resources/domino60.png",
		"src/resources/domino61.png",
		"src/resources/domino62.png",
		"src/resources/domino63.png",
		"src/resources/domino64.png",
		"src/resources/domino65.png",
		"src/resources/domino66.png"
	};
	
	public static ResourceMan storage;
	
	public static Game game;

	public static void main(String[] args) {
		
		storage = new ResourceMan(filesList);
		
		new Window(GAME_TITLE);
		
	}
}
