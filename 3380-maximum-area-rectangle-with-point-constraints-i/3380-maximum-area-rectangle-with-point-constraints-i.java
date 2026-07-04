import java.util.*;

class Solution {
    int[] tree;

    public int maxRectangleArea(int[][] points) {
        TreeSet<Integer> uniqueY = new TreeSet<>();
        for (int[] p : points) {
            uniqueY.add(p[1]);
        }
        
        Map<Integer, Integer> yToIndex = new HashMap<>();
        int idx = 0;
        for (int y : uniqueY) {
            yToIndex.put(y, idx++);
        }
        
        int m = uniqueY.size();
        tree = new int[4 * m];
        Arrays.fill(tree, -1);
        
        TreeMap<Integer, TreeSet<Integer>> xToY = new TreeMap<>();
        for (int[] p : points) {
            xToY.computeIfAbsent(p[0], k -> new TreeSet<>()).add(p[1]);
        }
        
        Map<Long, Integer> lastSeenX = new HashMap<>();
        int maxArea = -1;
        
        for (Map.Entry<Integer, TreeSet<Integer>> entry : xToY.entrySet()) {
            int x = entry.getKey();
            List<Integer> yList = new ArrayList<>(entry.getValue());
            
            for (int i = 0; i < yList.size() - 1; i++) {
                int y1 = yList.get(i);
                int y2 = yList.get(i + 1);
                long pairKey = ((long) y1 << 32) | (y2 & 0xFFFFFFFFL);
                
                if (lastSeenX.containsKey(pairKey)) {
                    int prevX = lastSeenX.get(pairKey);
                    int y1Idx = yToIndex.get(y1);
                    int y2Idx = yToIndex.get(y2);
                    
                    int maxST = query(1, 0, m - 1, y1Idx, y2Idx);
                    if (maxST == prevX) {
                        long area = (long) (x - prevX) * (y2 - y1);
                        if (area > maxArea) {
                            maxArea = (int) area;
                        }
                    }
                }
            }
            
            for (int i = 0; i < yList.size() - 1; i++) {
                int y1 = yList.get(i);
                int y2 = yList.get(i + 1);
                long pairKey = ((long) y1 << 32) | (y2 & 0xFFFFFFFFL);
                lastSeenX.put(pairKey, x);
            }
            
            for (int y : yList) {
                update(1, 0, m - 1, yToIndex.get(y), x);
            }
        }
        
        return maxArea;
    }
    
    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            tree[node] = Math.max(tree[node], val);
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(node * 2, start, mid, idx, val);
        } else {
            update(node * 2 + 1, mid + 1, end, idx, val);
        }
        tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
    }
    
    private int query(int node, int start, int end, int L, int R) {
        if (L > end || R < start) {
            return -1;
        }
        if (L <= start && end <= R) {
            return tree[node];
        }
        int mid = start + (end - start) / 2;
        int maxLeft = query(node * 2, start, mid, L, R);
        int maxRight = query(node * 2 + 1, mid + 1, end, L, R);
        return Math.max(maxLeft, maxRight);
    }
}