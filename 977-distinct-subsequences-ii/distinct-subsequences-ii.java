class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1000000007;
        int[] last = new int[26];
        int total = 0;

        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            int added = (total + 1) % MOD;
            total = (total + added) % MOD;
            total = (total - last[c] + MOD) % MOD;
            last[c] = added;
        }

        return total;
    }
}