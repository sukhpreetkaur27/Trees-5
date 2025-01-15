// LC 99
public class RecoverBST {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * NOTE: InOrder Traversal of a BST == Sorted
     * <p>
     * 2 cases:
     * 1. nodes to be swapped are adjacent --> 1 breach
     * 2. nodes to be swapped are not adjacent --> 2 breaches
     * <p>
     * swap the first breach element with the second breach element.
     * Update second breach element in case a second breach exists.
     * <p>
     * TC: O(n)
     * SC: O(H)
     *
     * @param root
     */
    public void recoverTree(TreeNode root) {
        TreeNode[] first = new TreeNode[1];
        TreeNode[] second = new TreeNode[1];
        inorder(root, new TreeNode[]{null}, first, second);
        swap(first[0], second[0]);
    }

    private void swap(TreeNode first, TreeNode second) {
        TreeNode temp = new TreeNode(first.val);
        first.val = second.val;
        second.val = temp.val;
    }

    private void inorder(TreeNode root, TreeNode[] prev, TreeNode[] first, TreeNode[] second) {
        if (root == null) {
            return;
        }
        inorder(root.left, prev, first, second);
        if (prev[0] != null && prev[0].val > root.val) {
            if (first[0] == null) {
                first[0] = prev[0];
            }
            second[0] = root;
        }
        prev[0] = root;
        inorder(root.right, prev, first, second);
    }
}
