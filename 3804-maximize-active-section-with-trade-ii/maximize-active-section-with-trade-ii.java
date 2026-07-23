import java.util.*;

class Solution {
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        
        int[] prefixOnes = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixOnes[i + 1] = prefixOnes[i] + (s.charAt(i) - '0');
        }
        int totalOnes = prefixOnes[n];
        
        int[] nextZero = new int[n];
        int lastZero = n;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') lastZero = i;
            nextZero[i] = lastZero;
        }
        
        int[] prevZero = new int[n];
        lastZero = -1;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') lastZero = i;
            prevZero[i] = lastZero;
        }
        
        List<ZeroGroup> groups = new ArrayList<>();
        int[] groupIndex = new int[n];
        Arrays.fill(groupIndex, -1);
        
        for (int i = 0; i < n; ) {
            if (s.charAt(i) == '0') {
                int j = i;
                while (j < n && s.charAt(j) == '0') j++;
                ZeroGroup g = new ZeroGroup(i, j - 1);
                groups.add(g);
                for (int k = i; k < j; k++) {
                    groupIndex[k] = groups.size() - 1;
                }
                i = j;
            } else {
                i++;
            }
        }
        
        int k = groups.size();
        int[] adjSum = new int[Math.max(0, k - 1)];
        for (int i = 0; i < k - 1; i++) {
            adjSum[i] = groups.get(i).length + groups.get(i + 1).length;
        }
        
        SegmentTree segTree = new SegmentTree(adjSum);
        List<Integer> ans = new ArrayList<>(queries.length);
        
        for (int i = 0; i < queries.length; i++) {
            int L = queries[i][0], R = queries[i][1];
            
            int l0 = nextZero[L];
            int r0 = prevZero[R];
            
            if (l0 > R || r0 < L || l0 > r0) {
                ans.add(totalOnes);
                continue;
            }
            
            int idxL = groupIndex[l0];
            int idxR = groupIndex[r0];
            
            if (idxL == idxR) {
                ans.add(totalOnes);
                continue;
            }
            
            int lenL = groups.get(idxL).end - l0 + 1;
            int lenR = r0 - groups.get(idxR).start + 1;
            
            if (idxL + 1 == idxR) {
                ans.add(totalOnes + lenL + lenR);
            } else {
                int gain1 = lenL + groups.get(idxL + 1).length;
                int gain2 = groups.get(idxR - 1).length + lenR;
                int gain3 = segTree.query(idxL + 1, idxR - 2);
                
                int maxGain = Math.max(gain1, Math.max(gain2, gain3));
                ans.add(totalOnes + maxGain);
            }
        }
        
        return ans;
    }
    
    static class ZeroGroup {
        int start, end, length;
        ZeroGroup(int start, int end) {
            this.start = start;
            this.end = end;
            this.length = end - start + 1;
        }
    }
    
    static class SegmentTree {
        int[] tree;
        int n;
        
        public SegmentTree(int[] arr) {
            n = arr.length;
            if (n > 0) {
                tree = new int[4 * n];
                build(arr, 0, 0, n - 1);
            }
        }
        
        private void build(int[] arr, int node, int start, int end) {
            if (start == end) {
                tree[node] = arr[start];
            } else {
                int mid = (start + end) / 2;
                build(arr, 2 * node + 1, start, mid);
                build(arr, 2 * node + 2, mid + 1, end);
                tree[node] = Math.max(tree[2 * node + 1], tree[2 * node + 2]);
            }
        }
        
        public int query(int l, int r) {
            if (n == 0 || l > r) return 0;
            return query(0, 0, n - 1, l, r);
        }
        
        private int query(int node, int start, int end, int l, int r) {
            if (r < start || end < l) return 0;
            if (l <= start && end <= r) return tree[node];
            
            int mid = (start + end) / 2;
            int p1 = query(2 * node + 1, start, mid, l, r);
            int p2 = query(2 * node + 2, mid + 1, end, l, r);
            return Math.max(p1, p2);
        }
    }
}