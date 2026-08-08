import java.util.Arrays;

class Solution {
    public int[] validSequence(String word1, String word2) {
        char[] w1 = word1.toCharArray();
        char[] w2 = word2.toCharArray();
        int n = w1.length;
        int m = w2.length;
        
        if (m > n) return new int[0];
        
        int[] right_exact = new int[m + 1];
        right_exact[m] = n;
        int curr = n - 1;
        for (int j = m - 1; j >= 0; j--) {
            while (curr >= 0 && w1[curr] != w2[j]) {
                curr--;
            }
            right_exact[j] = curr;
            if (curr >= 0) curr--;
        }
        
        int[] prev_occ = new int[n];
        int[] last = new int[26];
        Arrays.fill(last, -1);
        for(int i = 0; i < n; i++) {
            int c = w1[i] - 'a';
            prev_occ[i] = last[c];
            last[c] = i;
        }
        
        int[] right_almost = new int[m + 1];
        right_almost[m] = n;
        
        int ptr = n - 1;
        for (int j = m - 1; j >= 0; j--) {
            int opt1 = -1;
            if (right_exact[j + 1] != -1) {
                opt1 = right_exact[j + 1] - 1;
            }
            
            int opt2 = -1;
            if (right_almost[j + 1] != -1) {
                int limit = right_almost[j + 1] - 1;
                while (ptr > limit) {
                    if (ptr >= 0) {
                        int c = w1[ptr] - 'a';
                        if (last[c] == ptr) {
                            last[c] = prev_occ[ptr];
                        }
                    }
                    ptr--;
                }
                if (limit >= 0) {
                    opt2 = last[w2[j] - 'a'];
                }
            }
            
            right_almost[j] = Math.max(opt1, opt2);
        }
        
        if (right_almost[0] == -1) {
            return new int[0];
        }
        
        int[] ans = new int[m];
        boolean changed = false;
        int curr_i = 0;
        
        for (int k = 0; k < m; k++) {
            boolean found = false;
            for (int i = curr_i; i < n; i++) {
                if (changed) {
                    if (w1[i] == w2[k] && right_exact[k + 1] != -1 && i < right_exact[k + 1]) {
                        ans[k] = i;
                        curr_i = i + 1;
                        found = true;
                        break;
                    }
                } else {
                    if (w1[i] == w2[k]) {
                        if (right_almost[k + 1] != -1 && i < right_almost[k + 1]) {
                            ans[k] = i;
                            curr_i = i + 1;
                            found = true;
                            break;
                        }
                    } else {
                        if (right_exact[k + 1] != -1 && i < right_exact[k + 1]) {
                            ans[k] = i;
                            curr_i = i + 1;
                            changed = true;
                            found = true;
                            break;
                        }
                    }
                }
            }
            if (!found) {
                return new int[0]; 
            }
        }
        
        return ans;
    }
}