class Solution {
    public boolean hasAllCodes(String s, int k) {
        if (s.length() < (1 << k) + k - 1) {
            return false;
        }
        
        int need = 1 << k;
        boolean[] seen = new boolean[need];
        int val = 0;
        int allOnes = need - 1;
        
        for (int i = 0; i < s.length(); i++) {
            val = ((val << 1) & allOnes) | (s.charAt(i) - '0');
            
            if (i >= k - 1 && !seen[val]) {
                seen[val] = true;
                need--;
                
                if (need == 0) {
                    return true;
                }
            }
        }
        
        return false;
    }
}