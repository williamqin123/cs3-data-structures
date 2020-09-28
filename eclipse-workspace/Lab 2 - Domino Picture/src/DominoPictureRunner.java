
import java.io.IOException;

public class DominoPictureRunner {

	public static void main(String[] args) {
		try {
			new DominoPicture();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
