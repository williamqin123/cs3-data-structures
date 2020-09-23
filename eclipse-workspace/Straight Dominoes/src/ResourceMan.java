
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ResourceMan {
	
	private Map<String, BufferedImage> images = new HashMap<String, BufferedImage>();
	
	public ResourceMan(String[] filesList) {
		for (String path : filesList) {
			try {
				images.put(path.substring(path.lastIndexOf('/')+1), (BufferedImage)ImageIO.read(this.getClass().getResource(path)));
			}
			catch (Exception E) {
				System.out.println("Oopsie");
				return;
			}
		}
	}
	
	public Image get(String imageName) {
		return images.get(imageName);
	}
}
