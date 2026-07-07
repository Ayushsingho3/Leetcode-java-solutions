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
import java.util.ArrayList;
import java.util.List;

class Solution {
    public TreeNode balanceBST(TreeNode root) {
        List<TreeNode> sortedNodes = new ArrayList<>();
      
        inorderTraversal(root, sortedNodes);
        
        return buildBalancedBST(sortedNodes, 0, sortedNodes.size() - 1);
    }
    
    private void inorderTraversal(TreeNode node, List<TreeNode> sortedNodes) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left, sortedNodes);
        sortedNodes.add(node);
        inorderTraversal(node.right, sortedNodes);
    }
    
    private TreeNode buildBalancedBST(List<TreeNode> sortedNodes, int start, int end) {
       
        if (start > end) {
            return null;
        }
        
        int mid = start + (end - start) / 2;
        TreeNode node = sortedNodes.get(mid);
        
        node.left = buildBalancedBST(sortedNodes, start, mid - 1);
        node.right = buildBalancedBST(sortedNodes, mid + 1, end);
        
        return node;
    }
}