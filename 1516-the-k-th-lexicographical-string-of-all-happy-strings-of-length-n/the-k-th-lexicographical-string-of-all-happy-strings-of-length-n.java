class Solution {
    public String getHappyString(int n, int k) {
        int total = 3 * (1 << (n - 1));
        if (k > total) {
            return "";
        }
        
        k--;
        StringBuilder sb = new StringBuilder();
        
        int blockSize = 1 << (n - 1);
        int firstCharIdx = k / blockSize;
        char prev = (char) ('a' + firstCharIdx);
        sb.append(prev);
        k %= blockSize;
        
        for (int i = 1; i < n; i++) {
            blockSize = 1 << (n - 1 - i);
            int nextIdx = k / blockSize;
            k %= blockSize;
            
            int count = 0;
            for (char c = 'a'; c <= 'c'; c++) {
                if (c == prev) continue;
                if (count == nextIdx) {
                    sb.append(c);
                    prev = c;
                    break;
                }
                count++;
            }
        }
        
        return sb.toString();
    }
}