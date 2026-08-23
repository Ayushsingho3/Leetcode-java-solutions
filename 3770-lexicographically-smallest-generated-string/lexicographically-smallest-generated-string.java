import java.util.*;

class Solution {
    class DoubleHash {
        long[] h1, h2;
        long[] p1, p2;
        long MOD1 = 1_000_000_007L;
        long MOD2 = 1_000_000_009L;
        long BASE1 = 313L;
        long BASE2 = 317L;

        public DoubleHash(int[] arr) {
            int len = arr.length;
            h1 = new long[len + 1];
            h2 = new long[len + 1];
            p1 = new long[len + 1];
            p2 = new long[len + 1];
            p1[0] = 1;
            p2[0] = 1;
            for (int i = 0; i < len; i++) {
                h1[i + 1] = (h1[i] * BASE1 + arr[i]) % MOD1;
                h2[i + 1] = (h2[i] * BASE2 + arr[i]) % MOD2;
                p1[i + 1] = (p1[i] * BASE1) % MOD1;
                p2[i + 1] = (p2[i] * BASE2) % MOD2;
            }
        }

        public long getHash(int l, int r) {
            long hash1 = (h1[r + 1] - (h1[l] * p1[r - l + 1]) % MOD1) % MOD1;
            if (hash1 < 0) hash1 += MOD1;
            long hash2 = (h2[r + 1] - (h2[l] * p2[r - l + 1]) % MOD2) % MOD2;
            if (hash2 < 0) hash2 += MOD2;
            return (hash1 << 32) | hash2;
        }
    }

    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int L = n + m - 1;
        
        int[] initial_arr = new int[L];
        int last_T = -1;
        
        for (int p = 0; p < L; p++) {
            if (p < n && str1.charAt(p) == 'T') {
                last_T = p;
            }
            if (last_T != -1 && p - last_T < m) {
                initial_arr[p] = str2.charAt(p - last_T) - 'a' + 1;
            } else {
                initial_arr[p] = 0; 
            }
        }
        
        DoubleHash initial_hash = new DoubleHash(initial_arr);
        
        int[] str2_arr = new int[m];
        for (int i = 0; i < m; i++) {
            str2_arr[i] = str2.charAt(i) - 'a' + 1;
        }
        DoubleHash str2_hash = new DoubleHash(str2_arr);
        
        List<Integer>[] check_at = new ArrayList[L];
        for (int i = 0; i < L; i++) check_at[i] = new ArrayList<>();
        
        int[] prefix_zeros = new int[L + 1];
        for (int i = 0; i < L; i++) {
            prefix_zeros[i + 1] = prefix_zeros[i] + (initial_arr[i] == 0 ? 1 : 0);
        }
        
        int[] last_zero = new int[L];
        int curr_zero = -1;
        for (int p = 0; p < L; p++) {
            if (initial_arr[p] == 0) curr_zero = p;
            last_zero[p] = curr_zero;
        }
        
        for (int i = 0; i < n; i++) {
            int zeros = prefix_zeros[i + m] - prefix_zeros[i];
            if (zeros == 0) {
                long h = initial_hash.getHash(i, i + m - 1);
                long expected = str2_hash.getHash(0, m - 1);
                if (h == expected) {
                    if (str1.charAt(i) == 'F') return "";
                } else {
                    if (str1.charAt(i) == 'T') return "";
                }
            } else {
                if (str1.charAt(i) == 'F') {
                    int last_q = last_zero[i + m - 1]; 
                    check_at[last_q].add(i);
                }
            }
        }
        
        long MOD1 = 1_000_000_007L;
        long MOD2 = 1_000_000_009L;
        long BASE1 = 313L;
        long BASE2 = 317L;
        
        long[] fh1 = new long[L + 1], fh2 = new long[L + 1];
        long[] p1 = new long[L + 1], p2 = new long[L + 1];
        p1[0] = 1; p2[0] = 1;
        for (int i = 0; i < L; i++) {
            p1[i + 1] = (p1[i] * BASE1) % MOD1;
            p2[i + 1] = (p2[i] * BASE2) % MOD2;
        }
        
        char[] final_word = new char[L];
        for (int k = 0; k < L; k++) {
            if (initial_arr[k] != 0) { 
                final_word[k] = (char) (initial_arr[k] - 1 + 'a');
                fh1[k + 1] = (fh1[k] * BASE1 + initial_arr[k]) % MOD1;
                fh2[k + 1] = (fh2[k] * BASE2 + initial_arr[k]) % MOD2;
            } else { 
                boolean[] forbidden = new boolean[26];
                for (int i : check_at[k]) {
                    int prefix_len = k - i;
                    boolean match_prefix = false;
                    if (prefix_len == 0) {
                        match_prefix = true;
                    } else {
                        long hash1 = (fh1[k] - (fh1[i] * p1[prefix_len]) % MOD1) % MOD1;
                        if (hash1 < 0) hash1 += MOD1;
                        long hash2 = (fh2[k] - (fh2[i] * p2[prefix_len]) % MOD2) % MOD2;
                        if (hash2 < 0) hash2 += MOD2;
                        
                        long expected = str2_hash.getHash(0, prefix_len - 1);
                        match_prefix = ((hash1 << 32) | hash2) == expected;
                    }
                    
                    if (match_prefix) {
                        int suffix_len = i + m - 1 - k;
                        boolean match_suffix = false;
                        if (suffix_len == 0) {
                            match_suffix = true;
                        } else {
                            long expected = str2_hash.getHash(k + 1 - i, m - 1);
                            match_suffix = initial_hash.getHash(k + 1, i + m - 1) == expected;
                        }
                        
                        if (match_suffix) { 
                            forbidden[str2.charAt(k - i) - 'a'] = true;
                        }
                    }
                }
                
                char c = 'a';
                while (c <= 'z' && forbidden[c - 'a']) {
                    c++;
                }
                if (c > 'z') {
                    return "";
                }
                
                final_word[k] = c;
                int val = c - 'a' + 1;
                fh1[k + 1] = (fh1[k] * BASE1 + val) % MOD1;
                fh2[k + 1] = (fh2[k] * BASE2 + val) % MOD2;
            }
        }
        
        return new String(final_word);
    }
}