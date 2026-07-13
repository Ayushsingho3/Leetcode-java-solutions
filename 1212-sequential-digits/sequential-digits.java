import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        String digits = "123456789";
        int minLen = String.valueOf(low).length();
        int maxLen = String.valueOf(high).length();
        
        for (int len = minLen; len <= maxLen; len++) {
            for (int i = 0; i <= 9 - len; i++) {
                int num = Integer.parseInt(digits.substring(i, i + len));
                if (num >= low && num <= high) {
                    result.add(num);
                }
            }
        }
        
        return result;
    }
}