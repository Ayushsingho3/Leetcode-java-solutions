class Solution {
    public int minPartitions(String n) {
        int maxDigit = 0;
        
        for (int i = 0; i < n.length(); i++) {
            maxDigit = Math.max(maxDigit, n.charAt(i) - '0');
            
            if (maxDigit == 9) {
                break;
            }
        }
        
        return maxDigit;
    }
}