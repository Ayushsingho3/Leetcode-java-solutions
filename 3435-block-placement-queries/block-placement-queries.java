import java.util.*;

class Solution {
    static class SegmentTree {
        private int n;
        private int[] tree;

        public SegmentTree(int n) {
            this.n = n;
            this.tree = new int[4 * n];
        }

        public void update(int idx, int val) {
            update(1, 0, n - 1, idx, val);
        }

        private void update(int node, int l, int r, int idx, int val) {
            if (l == r) {
                tree[node] = val;
                return;
            }
            int mid = (l + r) / 2;
            if (idx <= mid) {
                update(node * 2, l, mid, idx, val);
            } else {
                update(node * 2 + 1, mid + 1, r, idx, val);
            }
            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
        }

        public int query(int L, int R) {
            if (L > R) return 0;
            return query(1, 0, n - 1, L, R);
        }

        private int query(int node, int l, int r, int L, int R) {
            if (R < l || r < L) {
                return 0;
            }
            if (L <= l && r <= R) {
                return tree[node];
            }
            int mid = (l + r) / 2;
            return Math.max(query(node * 2, l, mid, L, R), query(node * 2 + 1, mid + 1, r, L, R));
        }
    }

    public List<Boolean> getResults(int[][] queries) {
        int maxCoord = 0;
        for (int[] q : queries) {
            maxCoord = Math.max(maxCoord, q[1]);
        }
        maxCoord += 2;

        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        set.add(maxCoord);

        for (int[] q : queries) {
            if (q[0] == 1) {
                set.add(q[1]);
            }
        }

        SegmentTree seg = new SegmentTree(maxCoord + 1);
        Integer prev = 0;
        for (int curr : set) {
            if (curr > 0) {
                seg.update(curr, curr - prev);
            }
            prev = curr;
        }

        List<Boolean> ans = new ArrayList<>();
        for (int i = queries.length - 1; i >= 0; i--) {
            int type = queries[i][0];
            int x = queries[i][1];
            if (type == 1) {
                Integer next = set.higher(x);
                Integer floor = set.lower(x);
                if (next != null) {
                    seg.update(next, next - floor);
                }
                set.remove(x);
            } else {
                int sz = queries[i][2];
                Integer floor = set.floor(x);
                int bestGap = Math.max(seg.query(0, floor), x - floor);
                ans.add(bestGap >= sz);
            }
        }
        Collections.reverse(ans);
        return ans;
    }
}