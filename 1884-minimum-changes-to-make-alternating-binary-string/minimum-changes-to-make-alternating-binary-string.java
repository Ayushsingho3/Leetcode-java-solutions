class Solution {
    public int minOperations(String s) {
        int count = 0;
        int n = s.length();
        
        for (int i = 0; i < n; i++) {
            char expected = (i % 2 == 0) ? '0' : '1';
            if (s.charAt(i) != expected) {
                count++;
            }
        }
        
        return Math.min(count, n - count);
    }
}