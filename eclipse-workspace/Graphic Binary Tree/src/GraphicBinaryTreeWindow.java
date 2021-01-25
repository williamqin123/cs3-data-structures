
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicBinaryTreeWindow extends JFrame {

    final String title = "Graphic Binary Tree";

    public GraphicBinaryTreeWindow() {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setTitle(title);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        UserInputPanel userInputPanel = new UserInputPanel();
        GraphicBinaryTreeVisualizer graphicBinaryTreeVisualizer = new GraphicBinaryTreeVisualizer();

        BSTcontroller controller = new BSTcontroller(userInputPanel, graphicBinaryTreeVisualizer);

        add(userInputPanel);
        add(graphicBinaryTreeVisualizer);

        pack();
        setVisible(true);
    }

}

class UserInputPanel extends JPanel implements ActionListener {

    private BSTcontroller controller;

    JTextField numberField;
    JButton addNodeButton;

    public void setController(BSTcontroller c) {
        controller = c;
    }

    public UserInputPanel() {
        numberField = new JTextField(10);
        addNodeButton = new JButton("Add Node");

        add(numberField);
        add(addNodeButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addNodeButton) {
            controller.addNode(Double.parseDouble(numberField.getText()));
            numberField.setText(null);
        }
    }
}

class GraphicBinaryTreeVisualizer extends JPanel {

    final int width = 1200;
    final int height = 600;

    TreeNode tree;

    public GraphicBinaryTreeVisualizer() {
        setPreferredSize(new Dimension(width, height));
    }

    public void drawTree(TreeNode tree) {
        this.tree = tree;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;


    }
}