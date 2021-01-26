public class BSTcontroller {

    private TreeNode treeRoot;

    private UserInputPanel controls;
    private GraphicBinaryTreeVisualizer view;

    public BSTcontroller(UserInputPanel controls, GraphicBinaryTreeVisualizer view) {
        this.controls = controls;
        this.view = view;

        controls.setController(this);
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

    public int getDepth() {
        int depth = -1;
        TreeNode n = this;
        while (n != null) {
            ++depth;
            TreeNode leftChild = n.left();
            n = (leftChild == null) ? n.right() : leftChild;
        }
        return depth;
    }
}
