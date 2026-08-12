class Solution {
    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        java.util.TreeSet<Integer> set = new java.util.TreeSet<>();
        
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                set.add(grid[r][c]);
                if (set.size() > 3) {
                    set.pollFirst();
                }
                
                int maxL = Math.min(Math.min(c, n - 1 - c), (m - 1 - r) / 2);
                for (int L = 1; L <= maxL; L++) {
                    int sum = 0;
                    for (int i = 0; i < L; i++) {
                        sum += grid[r + i][c + i];
                    }
                    for (int i = 0; i < L; i++) {
                        sum += grid[r + L + i][c + L - i];
                    }
                    for (int i = 0; i < L; i++) {
                        sum += grid[r + 2 * L - i][c - i];
                    }
                    for (int i = 0; i < L; i++) {
                        sum += grid[r + L - i][c - L + i];
                    }
                    
                    set.add(sum);
                    if (set.size() > 3) {
                        set.pollFirst();
                    }
                }
            }
        }
        
        int[] result = new int[set.size()];
        int idx = result.length - 1;
        for (int val : set) {
            result[idx--] = val;
        }
        
        return result;
    }
}