import java.util.ArrayList;
import java.util.List;

// LC 94

/**
 * Morris Traversal uses threaded model of binary trees.
 * <p>
 * Works for InOrder and PreOrder Traversals.
 * <p>
 * Uses O(1) space by creating and pointing threads to fall back to the node to be backtracked to (Root)
 * had we followed the recursive stack trace.
 * <p>
 * InOrder == L Root R
 * We know that we have to connect the rightmost node of the left sub-tree to the root in order to backtrack to the root.
 */
public class MorrisInOrderTraversal {
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
     * TC: O(n).
     * <p>
     * For thread creation, the avg TC = O(1)
     * as Level 1 with the least # of nodes i.e. 1, takes at max h iterations
     * and Level h with the max nodes (leaf nodes) takes at max 0 iteration.
     * Hence, Overall TC: O(n) just for visiting or processing each node.
     * SC: O(1)
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode curr = root; // root of a tree
        while (curr != null) {
            TreeNode left = curr.left; // left pointer
            if (left != null) {
                // Create thread pointers
                TreeNode right = left; // right pointer
                // Thread from the rightmost node of the left sub tree to the root
                while (right.right != null && right.right != curr) {
                    right = right.right;
                }
                // if thread already exists, undo it.
                // print the Root and move to the right subtree
                if (right.right == curr) {
                    right.right = null;
                    res.add(curr.val);
                    curr = curr.right;
                } else {
                    // create thread and process the L subtree
                    right.right = curr;
                    curr = left;
                }
            } else {
                // L has been processed completely
                // move to its R, which will be either the Root (backtracked to) or the R subtree of this node itself.
                res.add(curr.val);
                curr = curr.right;
            }
        }
        return res;
    }
}
