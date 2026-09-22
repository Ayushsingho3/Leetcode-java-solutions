class Solution {
    private static class Node {
        int totalProd;
        int[] counts;

        Node(int k) {
            totalProd = 1 % k;
            counts = new int[k];
        }
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        int q = queries.length;

        Node[] tree = new Node[4 * n];
        buildTree(1, 0, n - 1, nums, k, tree);

        int[] result = new int[q];

        for (int i = 0; i < q; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            updateTree(1, 0, n - 1, idx, val, k, tree);

            Node queryNode = queryRange(1, 0, n - 1, start, n - 1, k, tree);
            result[i] = queryNode.counts[x];
        }

        return result;
    }

    private void buildTree(int node, int start, int end, int[] nums, int k, Node[] tree) {
        tree[node] = new Node(k);
        if (start == end) {
            int rem = nums[start] % k;
            tree[node].totalProd = rem;
            tree[node].counts[rem] = 1;
            return;
        }

        int mid = start + (end - start) / 2;
        buildTree(2 * node, start, mid, nums, k, tree);
        buildTree(2 * node + 1, mid + 1, end, nums, k, tree);

        mergeNodes(tree[node], tree[2 * node], tree[2 * node + 1], k);
    }

    private void updateTree(int node, int start, int end, int idx, int val, int k, Node[] tree) {
        if (start == end) {
            int rem = val % k;
            tree[node].totalProd = rem;
            for (int r = 0; r < k; r++) {
                tree[node].counts[r] = 0;
            }
            tree[node].counts[rem] = 1;
            return;
        }

        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            updateTree(2 * node, start, mid, idx, val, k, tree);
        } else {
            updateTree(2 * node + 1, mid + 1, end, idx, val, k, tree);
        }

        mergeNodes(tree[node], tree[2 * node], tree[2 * node + 1], k);
    }

    private Node queryRange(int node, int start, int end, int l, int r, int k, Node[] tree) {
        if (l <= start && end <= r) {
            return tree[node];
        }

        int mid = start + (end - start) / 2;
        if (r <= mid) {
            return queryRange(2 * node, start, mid, l, r, k, tree);
        }
        if (l > mid) {
            return queryRange(2 * node + 1, mid + 1, end, l, r, k, tree);
        }

        Node leftRes = queryRange(2 * node, start, mid, l, r, k, tree);
        Node rightRes = queryRange(2 * node + 1, mid + 1, end, l, r, k, tree);

        Node res = new Node(k);
        mergeNodes(res, leftRes, rightRes, k);
        return res;
    }

    private void mergeNodes(Node parent, Node left, Node right, int k) {
        parent.totalProd = (left.totalProd * right.totalProd) % k;

        for (int r = 0; r < k; r++) {
            parent.counts[r] = left.counts[r];
        }

        for (int r = 0; r < k; r++) {
            if (right.counts[r] > 0) {
                int combinedRem = (left.totalProd * r) % k;
                parent.counts[combinedRem] += right.counts[r];
            }
        }
    }
}