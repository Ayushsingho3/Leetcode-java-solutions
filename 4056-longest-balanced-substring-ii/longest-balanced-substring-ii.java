class Solution {
    public int longestBalanced(String s) {
        int n = s.length();
        if (n == 0) return 0;
        
        int maxLen = 0;
        
        int curLen = 1;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                curLen++;
            } else {
                maxLen = Math.max(maxLen, curLen);
                curLen = 1;
            }
        }
        maxLen = Math.max(maxLen, curLen);
        
        maxLen = Math.max(maxLen, solve2(s, 'a', 'b', 'c'));
        maxLen = Math.max(maxLen, solve2(s, 'b', 'c', 'a'));
        maxLen = Math.max(maxLen, solve2(s, 'c', 'a', 'b'));
        
        maxLen = Math.max(maxLen, solve3(s));
        
        return maxLen;
    }
    
    private int solve2(String s, char c1, char c2, char bad) {
        int n = s.length();
        int[] firstSeen = new int[2 * n + 1];
        java.util.Arrays.fill(firstSeen, -2);
        
        int[] modified = new int[2 * n + 1];
        int modCount = 0;
        
        firstSeen[n] = -1;
        modified[modCount++] = n;
        
        int diff = 0;
        int max = 0;
        
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == bad) {
                for (int j = 0; j < modCount; j++) {
                    firstSeen[modified[j]] = -2;
                }
                modCount = 0;
                diff = 0;
                firstSeen[n] = i;
                modified[modCount++] = n;
            } else {
                if (c == c1) diff++;
                else if (c == c2) diff--;
                
                int idx = diff + n;
                if (firstSeen[idx] != -2) {
                    max = Math.max(max, i - firstSeen[idx]);
                } else {
                    firstSeen[idx] = i;
                    modified[modCount++] = idx;
                }
            }
        }
        return max;
    }
    
    private int solve3(String s) {
        int n = s.length();
        int max = 0;
        java.util.HashMap<Long, Integer> map = new java.util.HashMap<>();
        
        long zeroKey = ((long)n << 32) | (long)n;
        map.put(zeroKey, -1);
        
        int ca = 0, cb = 0, cc = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'a') ca++;
            else if (c == 'b') cb++;
            else if (c == 'c') cc++;
            
            long k1 = ca - cb + n;
            long k2 = ca - cc + n;
            long key = (k1 << 32) | k2;
            
            if (map.containsKey(key)) {
                max = Math.max(max, i - map.get(key));
            } else {
                map.put(key, i);
            }
        }
        return max;
    }
}