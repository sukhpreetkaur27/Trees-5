import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
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

    /**
     * If more than 2 elements are swapped, then we follow the brute force.
     * <p>
     * Find the inOrder traversal of the tree.
     * Sort it.
     * Perform the inorder traversal and compare it with the sorted version on the fly.
     * <p>
     * TC: O(n log n) + O(n)
     * SC: O(n + h)
     *
     * @param root
     */
    public void recoverTree_moreThan2ElementsSwapped(TreeNode root) {
        List<Integer> order = new ArrayList<>();
        inorder(root, order);
        Collections.sort(order);
        inorder(root, new int[]{0}, order);
    }

    private void inorder(TreeNode root, int[] index, List<Integer> order) {
        if (root == null) {
            return;
        }
        inorder(root.left, index, order);
        if (order.get(index[0]) != root.val) {
            root.val = order.get(index[0]);
        }
        index[0]++;
        inorder(root.right, index, order);
    }

    private void inorder(TreeNode root, List<Integer> order) {
        if (root == null) {
            return;
        }
        inorder(root.left, order);
        order.add(root.val);
        inorder(root.right, order);
    }

}
