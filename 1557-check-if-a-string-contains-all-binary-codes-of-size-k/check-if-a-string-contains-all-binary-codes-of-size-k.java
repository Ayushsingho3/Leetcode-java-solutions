class Solution {
    public boolean hasAllCodes(String s, int k) {
        int needed = 1 << k;
        if (s.length() < needed + k - 1) {
            return false;
        }
        boolean[] seen = new boolean[needed];
        int hash = 0;
        int count = 0;
        int mask = needed - 1;
        
        for (int i = 0; i < s.length(); i++) {
            hash = ((hash << 1) & mask) | (s.charAt(i) - '0');
            if (i >= k - 1) {
                if (!seen[hash]) {
                    seen[hash] = true;
                    count++;
                    if (count == needed) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}