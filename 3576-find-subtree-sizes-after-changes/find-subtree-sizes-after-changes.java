import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<Integer>[] tree = new List[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }

        int[] newParent = Arrays.copyOf(parent, n);
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        findParents(0, tree, s, lastSeen, newParent);

        List<Integer>[] updatedTree = new List[n];
        for (int i = 0; i < n; i++) {
            updatedTree[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            updatedTree[newParent[i]].add(i);
        }

        int[] sizes = new int[n];
        computeSizes(0, updatedTree, sizes);
        return sizes;
    }

    private void findParents(int u, List<Integer>[] tree, String s, int[] lastSeen, int[] newParent) {
        int charIdx = s.charAt(u) - 'a';
        int prevAncestor = lastSeen[charIdx];

        if (prevAncestor != -1) {
            newParent[u] = prevAncestor;
        }

        lastSeen[charIdx] = u;
        for (int v : tree[u]) {
            findParents(v, tree, s, lastSeen, newParent);
        }
        lastSeen[charIdx] = prevAncestor;
    }

    private int computeSizes(int u, List<Integer>[] updatedTree, int[] sizes) {
        int total = 1;
        for (int v : updatedTree[u]) {
            total += computeSizes(v, updatedTree, sizes);
        }
        sizes[u] = total;
        return total;
    }
}