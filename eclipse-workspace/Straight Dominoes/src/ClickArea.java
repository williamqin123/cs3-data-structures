
public class ClickArea {
	public int x0, y0, x1, y1;
	
	private GameElement target;
	
	public void setTarget(GameElement t) {target = t;};
	public GameElement getTarget() {return target;};
	
	private String name = "";
	
	public void setName(String n) {name = n;}
	public String getName() {return name;}
	
	public ClickArea(int x0, int y0, int x1, int y1) {
		this.x0 = x0;
		this.y0 = y0;
		this.x1 = x1;
		this.y1 = y1;
	}
}
