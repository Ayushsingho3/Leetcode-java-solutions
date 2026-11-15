import java.util.Arrays;

class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        int m = restrictions.length;
        int[][] list = new int[m + 2][2];
        for (int i = 0; i < m; i++) {
            list[i] = restrictions[i];
        }
        list[m] = new int[]{1, 0};
        list[m + 1] = new int[]{n, n - 1};
        
        Arrays.sort(list, (a, b) -> Integer.compare(a[0], b[0]));
        
        int total = m + 2;
        for (int i = 1; i < total; i++) {
            list[i][1] = Math.min(list[i][1], list[i - 1][1] + (list[i][0] - list[i - 1][0]));
        }
        
        for (int i = total - 2; i >= 0; i--) {
            list[i][1] = Math.min(list[i][1], list[i + 1][1] + (list[i + 1][0] - list[i][0]));
        }
        
        int ans = 0;
        for (int i = 0; i < total - 1; i++) {
            int id1 = list[i][0], h1 = list[i][1];
            int id2 = list[i + 1][0], h2 = list[i + 1][1];
            int peak = (h1 + h2 + (id2 - id1)) / 2;
            ans = Math.max(ans, peak);
        }
        
        return ans;
    }
}