class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int maxArea = 0;
        
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] += matrix[i - 1][j];
                }
            }
        }
        
        for (int i = 0; i < m; i++) {
            java.util.Arrays.sort(matrix[i]);
            for (int j = n - 1; j >= 0; j--) {
                int height = matrix[i][j];
                if (height == 0) {
                    break;
                }
                int width = n - j;
                maxArea = Math.max(maxArea, height * width);
            }
        }
        
        return maxArea;
    }
}