
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
    final int canvasPadding = 20, nodeWidth = 50, nodeHeight = 24, horizontalGap = 10, levelGap = 15, textSize = 16;
    final Point nodePadding = new Point(2, (nodeHeight - textSize) / 2);

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

        g2d.translate(canvasPadding, canvasPadding);
        g2d.setFont(new Font("Arial", Font.PLAIN, textSize));

        drawTree(tree, new Point(0, 1), 0, g2d);
    }

    private int drawTree(TreeNode node, Point loc, int maxOffset, Graphics2D g2d) {

        final int nodeWidthAndMargin = nodeWidth + horizontalGap;

        int x = loc.x * nodeWidthAndMargin, y = loc.y * (nodeHeight + levelGap);

        g2d.drawString(GraphicBinaryTreeRunner.fmt(node.val()), x + nodePadding.x, y - nodePadding.y);

        g2d.drawRect(x, y - nodeHeight, nodeWidth, nodeHeight);

        final int halfNodeWidth = nodeWidth / 2;

        x += halfNodeWidth;

        TreeNode lChild = node.left(), rChild = node.right();

        int leftSubTreeHeight = 0;

        int offset = 0;

        if (lChild != null) {
            Point shiftedLoc = new Point(loc.x, loc.y + 1);
            g2d.drawLine(x, y, shiftedLoc.x * nodeWidthAndMargin + halfNodeWidth, y + levelGap);
            offset = drawTree(lChild, shiftedLoc, maxOffset, g2d);

            leftSubTreeHeight = lChild.height();
        }
        if (rChild != null) {
            int rightwardShift = 1 + (lChild == null ? 0 : lChild.netRight(0, 0, rChild.height()));

            Point shiftedLoc = new Point(loc.x + rightwardShift, loc.y + 1);
            g2d.drawLine(x, y, shiftedLoc.x * nodeWidthAndMargin + halfNodeWidth, y + levelGap);
            offset = drawTree(rChild, shiftedLoc, maxOffset, g2d) + 1;
        }

        return offset;
    }
}