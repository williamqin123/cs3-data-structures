
public class StraightDominoesApp {
	
	public static String GAME_TITLE = "Domino Wars";
	
	private final static String[] filesList = {
		"src/resources/new-bg.png",
			
		"src/resources/new-domino-00.png",
		"src/resources/new-domino-10.png",
		"src/resources/new-domino-11.png",
		"src/resources/new-domino-20.png",
		"src/resources/new-domino-21.png",
		"src/resources/new-domino-22.png",
		"src/resources/new-domino-30.png",
		"src/resources/new-domino-31.png",
		"src/resources/new-domino-32.png",
		"src/resources/new-domino-33.png",
		"src/resources/new-domino-40.png",
		"src/resources/new-domino-41.png",
		"src/resources/new-domino-42.png",
		"src/resources/new-domino-43.png",
		"src/resources/new-domino-44.png",
		"src/resources/new-domino-50.png",
		"src/resources/new-domino-51.png",
		"src/resources/new-domino-52.png",
		"src/resources/new-domino-53.png",
		"src/resources/new-domino-54.png",
		"src/resources/new-domino-55.png",
		"src/resources/new-domino-60.png",
		"src/resources/new-domino-61.png",
		"src/resources/new-domino-62.png",
		"src/resources/new-domino-63.png",
		"src/resources/new-domino-64.png",
		"src/resources/new-domino-65.png",
		"src/resources/new-domino-66.png",
		
		"src/resources/domino-shading.png",
		"src/resources/domino-3d.png",
		"src/resources/domino-shadow.png"
	};
	
	public static ResourceMan storage;
	
	public static Game game;
	
	public static Window window;

	public static void main(String[] args) {
		
		// Enables hardware acceleration
		System.setProperty("sun.java2d.opengl", "true");
		
		storage = new ResourceMan(filesList);
		
		window = new Window(GAME_TITLE);
		
	}
}
