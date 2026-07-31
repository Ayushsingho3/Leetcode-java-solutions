import java.util.Arrays;

class Solution {
    public int minimumPushes(String word) {
        int[] freq = new int[26];
        
        for (int i = 0; i < word.length(); i++) {
            freq[word.charAt(i) - 'a']++;
        }
        
        Arrays.sort(freq);
        
        int totalPushes = 0;
        int multiplier = 1;
        int count = 0;
        
        for (int i = 25; i >= 0; i--) {
            if (freq[i] == 0) {
                break;
            }
            
            totalPushes += freq[i] * multiplier;
            count++;
            
            if (count == 8) {
                multiplier++;
                count = 0;
            }
        }
        
        return totalPushes;
    }
}