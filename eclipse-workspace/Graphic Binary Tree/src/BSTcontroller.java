import com.sun.source.tree.Tree;

public class BSTcontroller {

    private TreeNode treeRoot;

    private UserInputPanel controls;
    private GraphicBinaryTreeVisualizer view;

    public BSTcontroller(UserInputPanel controls, GraphicBinaryTreeVisualizer view) {
        this.controls = controls;
        this.view = view;

        controls.setController(this);
    }

    private TreeNode[][] grid;

    public TreeNode[][] grid(TreeNode root) {
        int h = root.height();
        grid = new TreeNode[h + 1][(int)Math.pow(2, h)];
        putInGrid(root, 0, 0);
        return grid;
    }

    private void putInGrid(TreeNode n, int row, int col) {

    }

    public void addNode(double num) {

        TreeNode newNode = new TreeNode(num);

        if (treeRoot == null) {
            treeRoot = newNode;
            return;
        }

        TreeNode n = treeRoot;

        while (true) {
            if (newNode.val() <= n.val()) {
                TreeNode l = n.left();
                if (l == null) {
                    n.left(newNode);
                    break;
                }
                n = l;
                continue;
            }
            TreeNode r = n.right();
            if (r == null) {
                n.right(newNode);
                break;
            }
            n = r;
        }

        view.update(treeRoot);
    }
}

class TreeNode {

    public double val() {
        return val;
    }

    public void left(TreeNode l) {
        left = l;
    }

    public void right(TreeNode r) {
        right = r;
    }

    public TreeNode left() {
        return left;
    }

    public TreeNode right() {
        return right;
    }

    private final double val;
    private TreeNode left, right;

    public TreeNode(double val) {
        this.val = val;
    }

    public int height() {
        int height = -1;
        TreeNode n = this;
        while (n != null) {
            ++height;
            TreeNode leftChild = n.left();
            n = (leftChild == null) ? n.right() : leftChild;
        }
        return height;
    }

    public static int getMaxWidth(TreeNode node)
    {
        int maxWidth = 0;
        int width;
        int h = node.height();
        int i;

        /* Get width of each level and compare
           the width with maximum width so far */
        for (i = 1; i <= h; i++) {
            width = getWidth(node, i);
            if (width > maxWidth)
                maxWidth = width;
        }

        return maxWidth;
    }

    /* Get width of a given level */
    private static int getWidth(TreeNode node, int level)
    {
        if (node == null)
            return 0;

        if (level == 1)
            return 1;
        else if (level > 1)
            return getWidth(node.left, level - 1)
                    + getWidth(node.right, level - 1);
        return 0;
    }

    public int netRight(int x, int depth, int maxDepth) {

        if (depth >= maxDepth) return x;

        TreeNode r = right(), l = left();
        int xPos = x;
        if (r != null)
            x = Math.max(x, r.netRight(x + 1, depth + 1, maxDepth));
        if (l != null)
            x = Math.max(x, l.netRight(x, depth + 1, maxDepth));
        return xPos;
    }
}
