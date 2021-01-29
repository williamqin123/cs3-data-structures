
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class GraphicBinaryTreeWindow extends JFrame {

    final String title = "Graphic Binary Tree";

    private static JLabel getCenterAlignedJLabel(String text) {
        JLabel l = new JLabel(text, JLabel.CENTER);
        l.setAlignmentX(Component.CENTER_ALIGNMENT);
        return l;
    }

    public GraphicBinaryTreeWindow() {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setTitle(title);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        UserInputPanel userInputPanel = new UserInputPanel();
        GraphicBinaryTreeVisualizer graphicBinaryTreeVisualizer = new GraphicBinaryTreeVisualizer();

        BSTcontroller controller = new BSTcontroller(userInputPanel, graphicBinaryTreeVisualizer);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(getCenterAlignedJLabel("Type a decimal number in the box below and click the button to insert it as a tree node."));
        infoPanel.add(getCenterAlignedJLabel("You can also type a comma-separated series of numbers to add them to the tree, in the order that you typed them in, all in one go."));
        infoPanel.add(getCenterAlignedJLabel("Leave the box blank to insert a random number from 0 to 999."));

        add(infoPanel);
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
        numberField = new JTextField(25);
        addNodeButton = new JButton("Add Node(s)");
        addNodeButton.addActionListener(this);

        add(numberField);
        add(addNodeButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addNodeButton) {

            final String COMMA = ",";

            String text = numberField.getText();

            if (text.equals("")) {
                controller.addNode(Math.floor(Math.random() * 1000));
                return;
            }
            else if (text.contains(COMMA)) {
                String[] numbers = text.split(COMMA);
                for (String num : numbers) {
                    controller.addNode(Double.parseDouble(num));
                }
            }
            else {
                controller.addNode(Double.parseDouble(text));
            }
            numberField.setText(null);
        }
    }
}

class GraphicBinaryTreeVisualizer extends JPanel {

    final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    final int width = Math.min(1024, screenSize.width), height = Math.min(600, screenSize.height);
    int xCenter;
    final int canvasPadding = 10, nodeWidth = 40, nodeHeight = 20, horizontalGap = 5, levelGap = 14, textSize = 14;
    final Point nodePadding = new Point(2, (nodeHeight - textSize) / 2);
    final int halfNodeWidth = nodeWidth / 2, halfNodeHeight = nodeHeight / 2;

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

        xCenter = getWidth() / 2;

        Graphics2D g2d = (Graphics2D) g;

        g2d.setFont(new Font("Arial", Font.PLAIN, textSize));

        AffineTransform originalTransform = g2d.getTransform();

        g2d.translate(0, canvasPadding);

        int[] subtreeWidthBalance = tree.graphicBreadth(tree.height());
        g2d.translate((coordsToPix(new Point(subtreeWidthBalance[0] - subtreeWidthBalance[1], 0)).x - xCenter + halfNodeWidth) / 2, 0);

        drawTree(tree, new Point(0, 1), g2d);

        g2d.setTransform(originalTransform);
    }

    private Point coordsToPix(Point p) {
        return new Point((int)(xCenter + (p.x - 1.0) * halfNodeWidth + p.x * horizontalGap), p.y * (nodeHeight + levelGap));
    }

    private void drawTree(TreeNode node, Point loc, Graphics2D g2d) {
        AffineTransform originalTransform = g2d.getTransform();

        Point pixelLoc = coordsToPix(loc);

        g2d.drawString(GraphicBinaryTreeRunner.fmt(node.val()), pixelLoc.x + nodePadding.x, pixelLoc.y - nodePadding.y);

        g2d.drawRect(pixelLoc.x, pixelLoc.y - nodeHeight, nodeWidth, nodeHeight);

        g2d.translate(halfNodeWidth, 0);

        TreeNode lChild = node.left(), rChild = node.right();
        TreeNode[] children = {lChild, rChild};

        for (int i = children.length - 1; i >= 0; --i) {
            TreeNode n = children[i];
            if (n != null) {
                int otherDirection = (i + 1) % 2;
                TreeNode otherSide = children[otherDirection];
                //int shift = 1 + (false ? 0 : n.visualWidthInDirection(otherDirection, 0, 0, n.height()) * 2 /*+ otherSide.visualWidthInDirection(i, 0, 0, n.height())*/);

                int[] shift = n.graphicBreadth(Math.min(n.height(), otherSide == null ? 0 : otherSide.height()));

                Point shiftedLoc = new Point(loc.x + (i == 0 ? -shift[1] - 1 : shift[0] + 1), loc.y + 1);

                Point shiftedLocPix = coordsToPix(shiftedLoc);

                Point connectLineStart = (Point) pixelLoc.clone(), connectLineEnd = (Point) shiftedLocPix.clone();

                int dx = shiftedLoc.x - loc.x;
                int thirdNodeWidth = nodeWidth / 3;
                int sixthNodeWidth = nodeWidth / 6;
                if (dx >= 1) {
                    connectLineStart.x += sixthNodeWidth;
                    connectLineEnd.x -= sixthNodeWidth;
                }
                if (dx <= -1) {
                    connectLineStart.x -= sixthNodeWidth;
                    connectLineEnd.x += sixthNodeWidth;
                }
                if (dx >= 10 || dx <= -10) {
                    int thirdNodeHeight = nodeHeight / 3;
                    connectLineStart.y -= thirdNodeHeight;
                    connectLineEnd.y += thirdNodeHeight;
                }
                if (dx >= 3) {
                    connectLineStart.x += thirdNodeWidth;
                    connectLineEnd.x -= thirdNodeWidth;
                }
                if (dx <= -3) {
                    connectLineStart.x -= thirdNodeWidth;
                    connectLineEnd.x += thirdNodeWidth;
                }
                g2d.drawLine(connectLineStart.x, connectLineStart.y, connectLineEnd.x, connectLineEnd.y - nodeHeight);

                g2d.setTransform(originalTransform);

                drawTree(n, shiftedLoc, g2d);
            }
        }
    }
}