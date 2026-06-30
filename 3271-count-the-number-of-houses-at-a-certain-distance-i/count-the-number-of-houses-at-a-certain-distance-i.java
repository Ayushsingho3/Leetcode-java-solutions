class Solution {
    public int[] countOfPairs(int n, int x, int y) {
        int[] result = new int[n];
        
        if (x > y) {
            int temp = x;
            x = y;
            y = temp;
        }
        
        for (int i = 1; i <= n; i++) {
            int distToX = Math.abs(i - x);
            int distToY = Math.abs(i - y);
            
            for (int j = i + 1; j <= n; j++) {
                int minDist = j - i;
                
                int path2 = distToX + 1 + Math.abs(j - y);
                if (path2 < minDist) {
                    minDist = path2;
                }
                
                int path3 = distToY + 1 + Math.abs(j - x);
                if (path3 < minDist) {
                    minDist = path3;
                }
                
                result[minDist - 1] += 2;
            }
        }
        
        return result;
    }
}