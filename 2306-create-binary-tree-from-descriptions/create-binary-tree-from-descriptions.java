import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> map = new HashMap<>();
        Set<Integer> children = new HashSet<>();

        for (int[] d : descriptions) {
            int pVal = d[0];
            int cVal = d[1];
            int isLeft = d[2];

            TreeNode parent = map.computeIfAbsent(pVal, TreeNode::new);
            TreeNode child = map.computeIfAbsent(cVal, TreeNode::new);

            if (isLeft == 1) {
                parent.left = child;
            } else {
                parent.right = child;
            }
            children.add(cVal);
        }

        for (int[] d : descriptions) {
            int pVal = d[0];
            if (!children.contains(pVal)) {
                return map.get(pVal);
            }
        }

        return null;
    }
}