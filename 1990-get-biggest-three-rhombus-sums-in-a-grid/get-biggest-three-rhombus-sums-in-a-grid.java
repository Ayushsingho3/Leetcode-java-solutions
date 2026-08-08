import java.util.TreeSet;

class Solution {
    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        TreeSet<Integer> set = new TreeSet<>();
        
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                int maxL = Math.min(Math.min(c, n - 1 - c), (m - 1 - r) / 2);
                
                for (int L = 0; L <= maxL; L++) {
                    int sum = 0;
                    if (L == 0) {
                        sum = grid[r][c];
                    } else {
                        for (int i = 0; i < L; i++) {
                            sum += grid[r + i][c - i] 
                                 + grid[r + L + i][c - L + i] 
                                 + grid[r + 2 * L - i][c + i] 
                                 + grid[r + L - i][c + L - i];
                        }
                    }
                    
                    set.add(sum);
                    
                    if (set.size() > 3) {
                        set.pollFirst();
                    }
                }
            }
        }
        
        int[] res = new int[set.size()];
        int idx = 0;
        
        for (int val : set.descendingSet()) {
            res[idx++] = val;
        }
        
        return res;
    }
}