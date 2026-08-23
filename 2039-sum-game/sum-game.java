class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int sum1 = 0, sum2 = 0;
        int q1 = 0, q2 = 0;
        
        for (int i = 0; i < n / 2; i++) {
            char c = num.charAt(i);
            if (c == '?') {
                q1++;
            } else {
                sum1 += c - '0';
            }
        }
        
        for (int i = n / 2; i < n; i++) {
            char c = num.charAt(i);
            if (c == '?') {
                q2++;
            } else {
                sum2 += c - '0';
            }
        }
        
        if ((q1 + q2) % 2 != 0) {
            return true;
        }
        
        return (sum1 - sum2) * 2 != 9 * (q2 - q1);
    }
}