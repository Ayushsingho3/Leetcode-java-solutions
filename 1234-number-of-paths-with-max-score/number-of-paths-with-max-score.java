import java.util.List;

class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int MOD = 1000000007;
        
        int[][][] dp = new int[n][n][2];
        
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                dp[r][c][0] = -1;
            }
        }
        
        dp[n - 1][n - 1][0] = 0;
        dp[n - 1][n - 1][1] = 1;
        
        int[] dr = {1, 0, 1};
        int[] dc = {0, 1, 1};
        
        for (int r = n - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                if (r == n - 1 && c == n - 1) continue;
                
                char ch = board.get(r).charAt(c);
                if (ch == 'X') continue;
                
                int maxSum = -1;
                int paths = 0;
                
                for (int i = 0; i < 3; i++) {
                    int pr = r + dr[i];
                    int pc = c + dc[i];
                    
                    if (pr < n && pc < n && dp[pr][pc][0] != -1) {
                        if (dp[pr][pc][0] > maxSum) {
                            maxSum = dp[pr][pc][0];
                            paths = dp[pr][pc][1];
                        } else if (dp[pr][pc][0] == maxSum) {
                            paths = (paths + dp[pr][pc][1]) % MOD;
                        }
                    }
                }
                
                if (maxSum != -1) {
                    int val = 0;
                    if (ch >= '1' && ch <= '9') {
                        val = ch - '0';
                    }
                    dp[r][c][0] = maxSum + val;
                    dp[r][c][1] = paths;
                }
            }
        }
        
        if (dp[0][0][1] == 0) {
            return new int[]{0, 0};
        }
        
        return new int[]{dp[0][0][0], dp[0][0][1]};
    }
}