class Solution {
    public boolean hasAlternatingBits(int n) {
        long m = n ^ (n >> 1);
        return (m & (m + 1)) == 0;
    }
}