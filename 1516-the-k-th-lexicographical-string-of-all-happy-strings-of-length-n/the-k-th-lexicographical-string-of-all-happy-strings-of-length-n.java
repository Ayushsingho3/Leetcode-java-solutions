class Solution {
    public String getHappyString(int n, int k) {
        int total = 3 * (1 << (n - 1));
        if (k > total) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        k--;
        
        int blockSize = 1 << (n - 1);
        int firstCharIdx = k / blockSize;
        sb.append((char) ('a' + firstCharIdx));
        k %= blockSize;
        
        for (int i = 1; i < n; i++) {
            blockSize >>= 1;
            int nextChoice = k / blockSize;
            k %= blockSize;
            
            char prev = sb.charAt(sb.length() - 1);
            char nextChar;
            
            if (prev == 'a') {
                nextChar = (nextChoice == 0) ? 'b' : 'c';
            } else if (prev == 'b') {
                nextChar = (nextChoice == 0) ? 'a' : 'c';
            } else {
                nextChar = (nextChoice == 0) ? 'a' : 'b';
            }
            
            sb.append(nextChar);
        }
        
        return sb.toString();
    }
}