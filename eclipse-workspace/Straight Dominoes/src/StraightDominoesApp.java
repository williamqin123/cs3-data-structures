
public class StraightDominoesApp {
	
	public static String GAME_TITLE = "Domino Wars";
	
	private final static String[] filesList = {
		"/resources/new-bg.png",
			
		"/resources/new-domino-00.png",
		"/resources/new-domino-10.png",
		"/resources/new-domino-11.png",
		"/resources/new-domino-20.png",
		"/resources/new-domino-21.png",
		"/resources/new-domino-22.png",
		"/resources/new-domino-30.png",
		"/resources/new-domino-31.png",
		"/resources/new-domino-32.png",
		"/resources/new-domino-33.png",
		"/resources/new-domino-40.png",
		"/resources/new-domino-41.png",
		"/resources/new-domino-42.png",
		"/resources/new-domino-43.png",
		"/resources/new-domino-44.png",
		"/resources/new-domino-50.png",
		"/resources/new-domino-51.png",
		"/resources/new-domino-52.png",
		"/resources/new-domino-53.png",
		"/resources/new-domino-54.png",
		"/resources/new-domino-55.png",
		"/resources/new-domino-60.png",
		"/resources/new-domino-61.png",
		"/resources/new-domino-62.png",
		"/resources/new-domino-63.png",
		"/resources/new-domino-64.png",
		"/resources/new-domino-65.png",
		"/resources/new-domino-66.png",
		
		"/resources/domino-shading-h-3.png",
		//"/resources/domino-3d-h-2.png",
		"/resources/domino-shading-v-3.png",
		//"/resources/domino-3d-v-2.png",
		"/resources/domino-shadow.png",
		
		"/resources/blue-rect.png",
		"/resources/blue-rect-hover.png"
	};
	
	public static ResourceMan storage;
	
	public static Game game;
	
	public static Window window;

	public static void main(String[] args) {
		
		// Enables hardware acceleration
		//System.setProperty("sun.java2d.opengl", "true");
		
		System.setProperty("sun.java2d.accthreshold", "0");
		
		storage = new ResourceMan(filesList);
		
		window = new Window(GAME_TITLE);
		
		game = new Game(window.viewport);
		game.start();
	}
}
