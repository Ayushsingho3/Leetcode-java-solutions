class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        
        int[][] xCount = new int[m][n];
        int[][] yCount = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int currX = (grid[i][j] == 'X') ? 1 : 0;
                int currY = (grid[i][j] == 'Y') ? 1 : 0;
                
                if (i > 0) {
                    currX += xCount[i - 1][j];
                    currY += yCount[i - 1][j];
                }
                if (j > 0) {
                    currX += xCount[i][j - 1];
                    currY += yCount[i][j - 1];
                }
                if (i > 0 && j > 0) {
                    currX -= xCount[i - 1][j - 1];
                    currY -= yCount[i - 1][j - 1];
                }
                
                xCount[i][j] = currX;
                yCount[i][j] = currY;
                
                if (currX == currY && currX > 0) {
                    count++;
                }
            }
        }
        
        return count;
    }
}