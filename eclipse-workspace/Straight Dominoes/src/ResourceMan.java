
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ResourceMan {
	
	private Map<String, BufferedImage> images = new HashMap<String, BufferedImage>();
	
	private BufferedImage toCompatibleImage(BufferedImage image) {
	    // obtain the current system graphical settings
	    GraphicsConfiguration gfxConfig = GraphicsEnvironment.
	        getLocalGraphicsEnvironment().getDefaultScreenDevice().
	        getDefaultConfiguration();

	    /*
	     * if image is already compatible and optimized for current system 
	     * settings, simply return it
	     */
	    if (image.getColorModel().equals(gfxConfig.getColorModel()))
	        return image;

	    // image is not optimized, so create a new image that is
	    BufferedImage newImage = gfxConfig.createCompatibleImage(
	            image.getWidth(), image.getHeight(), image.getTransparency());

	    // get the graphics context of the new image to draw the old image on
	    Graphics2D g2d = newImage.createGraphics();

	    // actually draw the image and dispose of context no longer needed
	    g2d.drawImage(image, 0, 0, null);
	    g2d.dispose();

	    // return the new optimized image
	    return newImage; 
	}
	
	public ResourceMan(String[] filesList) {
		for (String path : filesList) {
			try {
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			    GraphicsDevice gs = ge.getDefaultScreenDevice();
			    GraphicsConfiguration gc = gs.getDefaultConfiguration();
			    
			    Image originalImage = ImageIO.read(this.getClass().getResource(path));
			    
			    BufferedImage bufferedImage = toCompatibleImage((BufferedImage)originalImage);
			    bufferedImage.setAccelerationPriority(1);
			    
				images.put(path.substring(path.lastIndexOf('/')+1), bufferedImage);
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
