import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    int[] head, to, next;
    int edgeCount;
    int[] dep, parent, heavy, headChain, pos;
    int timer;
    int[] tree;
    int[] val;
    int n;

    void addEdge(int u, int v) {
        to[edgeCount] = v;
        next[edgeCount] = head[u];
        head[u] = edgeCount++;
    }

    int dfs1(int u, int p, int d) {
        dep[u] = d;
        parent[u] = p;
        int size = 1, maxSub = 0;
        heavy[u] = -1;
        for (int e = head[u]; e != -1; e = next[e]) {
            int v = to[e];
            if (v != p) {
                int sub = dfs1(v, u, d + 1);
                size += sub;
                if (sub > maxSub) {
                    maxSub = sub;
                    heavy[u] = v;
                }
            }
        }
        return size;
    }

    void dfs2(int u, int h) {
        headChain[u] = h;
        pos[u] = ++timer;
        if (heavy[u] != -1) {
            dfs2(heavy[u], h);
        }
        for (int e = head[u]; e != -1; e = next[e]) {
            int v = to[e];
            if (v != parent[u] && v != heavy[u]) {
                dfs2(v, v);
            }
        }
    }

    void updateTree(int node, int l, int r, int idx, int valMask) {
        if (l == r) {
            tree[node] = valMask;
            return;
        }
        int mid = (l + r) / 2;
        if (idx <= mid) updateTree(node * 2, l, mid, idx, valMask);
        else updateTree(node * 2 + 1, mid + 1, r, idx, valMask);
        tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
    }

    int queryTree(int node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) return 0;
        if (ql <= l && r <= qr) return tree[node];
        int mid = (l + r) / 2;
        return queryTree(node * 2, l, mid, ql, qr) ^ queryTree(node * 2 + 1, mid + 1, r, ql, qr);
    }

    int queryPath(int u, int v) {
        int res = 0;
        while (headChain[u] != headChain[v]) {
            if (dep[headChain[u]] < dep[headChain[v]]) {
                int t = u; u = v; v = t;
            }
            res ^= queryTree(1, 1, n, pos[headChain[u]], pos[u]);
            u = parent[headChain[u]];
        }
        if (dep[u] > dep[v]) {
            int t = u; u = v; v = t;
        }
        res ^= queryTree(1, 1, n, pos[u], pos[v]);
        return res;
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        head = new int[n];
        Arrays.fill(head, -1);
        to = new int[2 * n];
        next = new int[2 * n];
        edgeCount = 0;

        for (int[] e : edges) {
            addEdge(e[0], e[1]);
            addEdge(e[1], e[0]);
        }

        dep = new int[n];
        parent = new int[n];
        heavy = new int[n];
        headChain = new int[n];
        pos = new int[n];
        val = new int[n];

        dfs1(0, -1, 0);
        timer = 0;
        dfs2(0, 0);

        tree = new int[4 * n + 1];
        char[] charArr = s.toCharArray();
        for (int i = 0; i < n; i++) {
            val[i] = 1 << (charArr[i] - 'a');
            updateTree(1, 1, n, pos[i], val[i]);
        }

        List<Boolean> result = new ArrayList<>();
        for (String qStr : queries) {
            String[] parts = qStr.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                val[u] = 1 << (c - 'a');
                updateTree(1, 1, n, pos[u], val[u]);
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int mask = queryPath(u, v);
                result.add((mask & (mask - 1)) == 0);
            }
        }
        return result;
    }
}