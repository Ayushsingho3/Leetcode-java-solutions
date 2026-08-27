class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        
        int maxMatch = 0;
        while (maxMatch < n && count[target.charAt(maxMatch) - 'a'] > 0) {
            count[target.charAt(maxMatch) - 'a']--;
            maxMatch++;
        }
        
        if (maxMatch == n) {
            maxMatch--;
            count[target.charAt(maxMatch) - 'a']++;
        }
        
        for (int i = maxMatch; i >= 0; i--) {
            char t = target.charAt(i);
            
            for (int c = t - 'a' + 1; c < 26; c++) {
                if (count[c] > 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(target.substring(0, i));
                    sb.append((char) (c + 'a'));
                    count[c]--;
                    
                    for (int j = 0; j < 26; j++) {
                        while (count[j] > 0) {
                            sb.append((char) (j + 'a'));
                            count[j]--;
                        }
                    }
                    return sb.toString();
                }
            }
            
            if (i > 0) {
                count[target.charAt(i - 1) - 'a']++;
            }
        }
        
        return "";
    }
}