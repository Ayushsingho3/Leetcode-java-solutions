class Solution {
    public int minFlips(String s) {
        int n = s.length();
        int diff1 = 0;
        int diff2 = 0;
        int minFlips = Integer.MAX_VALUE;
        
        for (int i = 0; i < 2 * n; i++) {
            char c = s.charAt(i % n);
            char alt1 = (i % 2 == 0) ? '0' : '1';
            char alt2 = (i % 2 == 0) ? '1' : '0';
            
            if (c != alt1) diff1++;
            if (c != alt2) diff2++;
            
            if (i >= n) {
                int left = i - n;
                char leftC = s.charAt(left % n);
                char leftAlt1 = (left % 2 == 0) ? '0' : '1';
                char leftAlt2 = (left % 2 == 0) ? '1' : '0';
                
                if (leftC != leftAlt1) diff1--;
                if (leftC != leftAlt2) diff2--;
            }
            
            if (i >= n - 1) {
                minFlips = Math.min(minFlips, Math.min(diff1, diff2));
            }
        }
        
        return minFlips;
    }
}