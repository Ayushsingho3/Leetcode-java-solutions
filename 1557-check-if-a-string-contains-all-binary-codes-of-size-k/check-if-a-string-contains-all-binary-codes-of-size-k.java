class Solution {
    public boolean hasAllCodes(String s, int k) {
        int needed = 1 << k; // 2^k
        
        if (s.length() < needed + k - 1) {
            return false;
        }
        
        Set<String> codes = new HashSet<>();
        
        for (int i = 0; i <= s.length() - k; i++) {
            codes.add(s.substring(i, i + k));
        }
        
        return codes.size() == needed;
    }
}