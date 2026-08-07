class Solution {
    private static final int[][] dp = new int[65][45];
    
    static {
        for (int i = 0; i <= 60; i++) {
            for (int j = 0; j <= 40; j++) {
                if (i == 0 && j == 0) continue;
                dp[i][j] = 1000000;
                dp[i][j] = Math.min(dp[i][j], 1 + dp[Math.max(0, i - 1)][j]); 
                dp[i][j] = Math.min(dp[i][j], 1 + dp[i][Math.max(0, j - 1)]); 
                dp[i][j] = Math.min(dp[i][j], 1 + dp[Math.max(0, i - 2)][j]); 
                dp[i][j] = Math.min(dp[i][j], 1 + dp[Math.max(0, i - 1)][Math.max(0, j - 1)]); 
                dp[i][j] = Math.min(dp[i][j], 1 + dp[Math.max(0, i - 3)][j]); 
                dp[i][j] = Math.min(dp[i][j], 1 + dp[i][Math.max(0, j - 2)]); 
            }
        }
    }

    public String smallestNumber(String num, long t) {
        long temp = t;
        int reqTwos = 0, reqThrees = 0, reqFives = 0, reqSevens = 0;
        
        while (temp % 2 == 0) { reqTwos++; temp /= 2; }
        while (temp % 3 == 0) { reqThrees++; temp /= 3; }
        while (temp % 5 == 0) { reqFives++; temp /= 5; }
        while (temp % 7 == 0) { reqSevens++; temp /= 7; }
        
        if (temp > 1) {
            return "-1";
        }
        
        int[] twos = {0, 0, 1, 0, 2, 0, 1, 0, 3, 0};
        int[] threes = {0, 0, 0, 1, 0, 0, 1, 0, 0, 2};
        int[] fives = {0, 0, 0, 0, 0, 1, 0, 0, 0, 0};
        int[] sevens = {0, 0, 0, 0, 0, 0, 0, 1, 0, 0};
        
        int n = num.length();
        int firstZero = num.indexOf('0');
        if (firstZero == -1) firstZero = n;
        
        int aPref = 0, bPref = 0, cPref = 0, dPref = 0;
        for (int j = 0; j < firstZero; j++) {
            int d = num.charAt(j) - '0';
            aPref += twos[d];
            bPref += threes[d];
            cPref += fives[d];
            dPref += sevens[d];
        }
        
        for (int i = firstZero; i >= 0; i--) {
            int A = Math.max(0, reqTwos - aPref);
            int B = Math.max(0, reqThrees - bPref);
            int C = Math.max(0, reqFives - cPref);
            int D = Math.max(0, reqSevens - dPref);
            
            if (i == n) {
                if (A == 0 && B == 0 && C == 0 && D == 0) {
                    return num;
                }
            } else {
                int start = Math.max(1, num.charAt(i) - '0' + 1);
                for (int v = start; v <= 9; v++) {
                    int A_prime = Math.max(0, A - twos[v]);
                    int B_prime = Math.max(0, B - threes[v]);
                    int C_prime = Math.max(0, C - fives[v]);
                    int D_prime = Math.max(0, D - sevens[v]);
                    
                    if (C_prime + D_prime + dp[A_prime][B_prime] <= n - 1 - i) {
                        return build(num.substring(0, i) + v, n, reqTwos, reqThrees, reqFives, reqSevens, twos, threes, fives, sevens);
                    }
                }
            }
            
            if (i > 0) {
                int d = num.charAt(i - 1) - '0';
                aPref -= twos[d];
                bPref -= threes[d];
                cPref -= fives[d];
                dPref -= sevens[d];
            }
        }
        
        int L = reqFives + reqSevens + dp[reqTwos][reqThrees];
        return build("", Math.max(n + 1, L), reqTwos, reqThrees, reqFives, reqSevens, twos, threes, fives, sevens);
    }
    
    private String build(String prefix, int targetLen, int A, int B, int C, int D, int[] twos, int[] threes, int[] fives, int[] sevens) {
        StringBuilder sb = new StringBuilder(prefix);
        for (int i = 0; i < prefix.length(); i++) {
            int d = prefix.charAt(i) - '0';
            A = Math.max(0, A - twos[d]);
            B = Math.max(0, B - threes[d]);
            C = Math.max(0, C - fives[d]);
            D = Math.max(0, D - sevens[d]);
        }
        
        int remaining = targetLen - prefix.length();
        for (int k = remaining; k >= 1; k--) {
            for (int v = 1; v <= 9; v++) {
                int A_prime = Math.max(0, A - twos[v]);
                int B_prime = Math.max(0, B - threes[v]);
                int C_prime = Math.max(0, C - fives[v]);
                int D_prime = Math.max(0, D - sevens[v]);
                
                if (C_prime + D_prime + dp[A_prime][B_prime] <= k - 1) {
                    sb.append(v);
                    A = A_prime;
                    B = B_prime;
                    C = C_prime;
                    D = D_prime;
                    break;
                }
            }
        }
        
        return sb.toString();
    }
}