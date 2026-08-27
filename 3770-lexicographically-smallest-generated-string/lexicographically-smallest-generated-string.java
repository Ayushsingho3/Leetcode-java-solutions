class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int sz = n + m - 1;
        
        char[] ans = new char[sz];
        boolean[] fixed = new boolean[sz];
        
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    int pos = i + j;
                    if (ans[pos] != '\0' && ans[pos] != str2.charAt(j)) {
                        return "";
                    }
                    ans[pos] = str2.charAt(j);
                    fixed[pos] = true;
                }
            }
        }
        
        for (int i = 0; i < sz; i++) {
            if (ans[i] == '\0') {
                ans[i] = 'a';
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F') {
                boolean match = true;
                for (int j = 0; j < m; j++) {
                    if (ans[i + j] != str2.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                
                if (match) {
                    int changePos = -1;
                    
                    for (int j = m - 1; j >= 0; j--) {
                        if (!fixed[i + j]) {
                            changePos = i + j;
                            break;
                        }
                    }
                    
                    if (changePos == -1) {
                        return "";
                    }
                    
                    ans[changePos] = 'b';
                    fixed[changePos] = true;
                }
            }
        }
        
        return new String(ans);
    }
}