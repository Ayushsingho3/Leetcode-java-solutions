class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int count = 0;
        int lastAvailable = 0;

        for (int i = 0; i < n; i++) {
            if (i - k + 1 >= lastAvailable && isPalindrome(s, i - k + 1, i)) {
                count++;
                lastAvailable = i + 1;
            } else if (i - k >= lastAvailable && isPalindrome(s, i - k, i)) {
                count++;
                lastAvailable = i + 1;
            }
        }

        return count;
    }

    private boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}