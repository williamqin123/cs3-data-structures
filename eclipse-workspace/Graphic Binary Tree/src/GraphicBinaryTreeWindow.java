
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

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
        addNodeButton.addActionListener(this);

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

    final int width = 1200, height = 600;
    final int nodeWidth = 20, nodeHeight = 16, levelGap = 8, textSize = 12;

    TreeNode tree;

    public GraphicBinaryTreeVisualizer() {
        setPreferredSize(new Dimension(width, height));
    }

    public void update(TreeNode tree) {
        this.tree = tree;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (tree == null) return;

        Graphics2D g2d = (Graphics2D) g;

        drawTree(tree, new Point(0, 1), g2d);
    }

    private void drawTree(TreeNode node, Point loc, Graphics2D g2d) {

        System.out.println(node.val());

        int x = loc.x * nodeWidth, y = loc.y * nodeHeight;

        g2d.drawString(Double.toString(node.val()), x, y);

        TreeNode lChild = node.left(), rChild = node.right();

        if (lChild != null) {
            Point shiftedLoc = new Point(loc.x, loc.y + 1);
            g2d.drawLine(x, y, shiftedLoc.x * nodeWidth, y + levelGap);
            drawTree(lChild, shiftedLoc, g2d);
        }
        if (rChild != null) {
            Point shiftedLoc = new Point(loc.x + (int)Math.pow(2, node.getDepth()), loc.y + 1);
            g2d.drawLine(x + nodeWidth, y, shiftedLoc.x * nodeWidth, y + levelGap);
            drawTree(rChild, shiftedLoc, g2d);
        }
    }
}