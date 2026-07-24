class Solution {
    public int minSwaps(int[][] grid) {
        int n = grid.length;
        int[] pos = new int[n];
        
        for (int i = 0; i < n; i++) {
            int lastOne = -1;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    lastOne = j;
                }
            }
            pos[i] = lastOne;
        }
        
        int swaps = 0;
        
        for (int i = 0; i < n; i++) {
            int j = i;
            while (j < n && pos[j] > i) {
                j++;
            }
            
            if (j == n) {
                return -1;
            }
            
            while (j > i) {
                int temp = pos[j];
                pos[j] = pos[j - 1];
                pos[j - 1] = temp;
                swaps++;
                j--;
            }
        }
        
        return swaps;
    }
}