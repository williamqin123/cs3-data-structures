
import java.awt.*;
import javax.swing.*;

public class FourCorners extends JFrame {
	
	private final static String NAME = "Four Corners";
	
	private final static int MARGIN_WIDTH = 20;

	public FourCorners() {
		super(NAME);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit.getDefaultToolkit().setDynamicLayout(true);

		Container cont = getContentPane();
		
		// minimum window size = big enough to fit the 4 corner images without overlapping with margins between them the same size as the margins between the images and the edges of the window
		cont.setMinimumSize(new Dimension(CornerImg.DEFAULT_SIZE * 2 + MARGIN_WIDTH * 3, CornerImg.DEFAULT_SIZE * 2 + MARGIN_WIDTH * 3));
		
		cont.setPreferredSize(new Dimension(800, 800));
		
		SpringLayout layout = new SpringLayout();
		cont.setLayout(layout);
		
		JLabel appName = new JLabel(NAME);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, appName, 0, SpringLayout.HORIZONTAL_CENTER, cont);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, appName, 0, SpringLayout.VERTICAL_CENTER, cont);
		cont.add(appName);
		
		CornerImg NWim = new CornerImg("pictures/texas-rangers-new.png", "pictures/texas-rangers-old.png");
		layout.putConstraint(SpringLayout.WEST, NWim, MARGIN_WIDTH, SpringLayout.WEST, cont);
		layout.putConstraint(SpringLayout.NORTH, NWim, MARGIN_WIDTH, SpringLayout.NORTH, cont);
		cont.add(NWim);
		cont.setComponentZOrder(NWim, 0);
		
		CornerImg NEim = new CornerImg("pictures/houston-astros-new.png", "pictures/houston-astros-old.png");
		layout.putConstraint(SpringLayout.EAST, NEim, -MARGIN_WIDTH, SpringLayout.EAST, cont);
		layout.putConstraint(SpringLayout.NORTH, NEim, MARGIN_WIDTH, SpringLayout.NORTH, cont);
		cont.add(NEim);
		cont.setComponentZOrder(NEim, 1);
		
		CornerImg SWim = new CornerImg("pictures/dallas-mavericks-new.png", "pictures/dallas-mavericks-old.png");
		layout.putConstraint(SpringLayout.WEST, SWim, MARGIN_WIDTH, SpringLayout.WEST, cont);
		layout.putConstraint(SpringLayout.SOUTH, SWim, -MARGIN_WIDTH, SpringLayout.SOUTH, cont);
		cont.add(SWim);
		cont.setComponentZOrder(SWim, 2);
		
		CornerImg SEim = new CornerImg("pictures/houston-rockets-new.png", "pictures/houston-rockets-old.png");
		layout.putConstraint(SpringLayout.EAST, SEim, -MARGIN_WIDTH, SpringLayout.EAST, cont);
		layout.putConstraint(SpringLayout.SOUTH, SEim, -MARGIN_WIDTH, SpringLayout.SOUTH, cont);
		cont.add(SEim);
		cont.setComponentZOrder(SEim, 3);
		
		cont.setComponentZOrder(appName, 4);
		
		pack();
		setVisible(true);
	}
}
