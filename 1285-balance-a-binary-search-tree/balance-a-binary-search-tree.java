/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private TreeNode[] nodes;
    private int index = 0;

    public TreeNode balanceBST(TreeNode root) {
        int count = countNodes(root);
        nodes = new TreeNode[count];
        index = 0;
        
        inorder(root);
        
        return build(0, count - 1);
    }

    private int countNodes(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    private void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        nodes[index++] = node;
        inorder(node.right);
    }

    private TreeNode build(int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode node = nodes[mid];
        
        node.left = build(left, mid - 1);
        node.right = build(mid + 1, right);
        
        return node;
    }
}