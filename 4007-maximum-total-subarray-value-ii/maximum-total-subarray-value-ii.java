import java.util.*;

class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        SparseTable st = new SparseTable(nums);
        
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(b[0], a[0]));
        
        for (int l = 0; l < n; l++) {
            long val = (long) st.queryMax(l, n - 1) - st.queryMin(l, n - 1);
            pq.offer(new long[] {val, l, n - 1});
        }
        
        long totalValue = 0;
        for (int i = 0; i < k; i++) {
            long[] curr = pq.poll();
            long val = curr[0];
            int l = (int) curr[1];
            int r = (int) curr[2];
            
            totalValue += val;
            
            if (r > l) {
                long nextVal = (long) st.queryMax(l, r - 1) - st.queryMin(l, r - 1);
                pq.offer(new long[] {nextVal, l, r - 1});
            }
        }
        
        return totalValue;
    }
    
    private static class SparseTable {
        int[][] maxSt, minSt;
        int[] logTable;
        
        public SparseTable(int[] nums) {
            int n = nums.length;
            int maxLog = 32 - Integer.numberOfLeadingZeros(n);
            maxSt = new int[n][maxLog];
            minSt = new int[n][maxLog];
            logTable = new int[n + 1];
            
            for (int i = 2; i <= n; i++) {
                logTable[i] = logTable[i >> 1] + 1;
            }
            
            for (int i = 0; i < n; i++) {
                maxSt[i][0] = nums[i];
                minSt[i][0] = nums[i];
            }
            
            for (int j = 1; j < maxLog; j++) {
                for (int i = 0; i <= n - (1 << j); i++) {
                    maxSt[i][j] = Math.max(maxSt[i][j - 1], maxSt[i + (1 << (j - 1))][j - 1]);
                    minSt[i][j] = Math.min(minSt[i][j - 1], minSt[i + (1 << (j - 1))][j - 1]);
                }
            }
        }
        
        public int queryMax(int l, int r) {
            int j = logTable[r - l + 1];
            return Math.max(maxSt[l][j], maxSt[r - (1 << j) + 1][j]);
        }
        
        public int queryMin(int l, int r) {
            int j = logTable[r - l + 1];
            return Math.min(minSt[l][j], minSt[r - (1 << j) + 1][j]);
        }
    }
}