import java.util.HashSet;
import java.util.Set;

class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        Set<Integer> set = new HashSet<>();
        for (int x : arr1) {
            while (x > 0) {
                set.add(x);
                x /= 10;
            }
        }
        
        int maxLen = 0;
        for (int y : arr2) {
            while (y > 0) {
                if (set.contains(y)) {
                    maxLen = Math.max(maxLen, String.valueOf(y).length());
                    break;
                }
                y /= 10;
            }
        }
        
        return maxLen;
    }
}