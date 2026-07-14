class Solution {
    public int countPrimeSetBits(int left, int right) {
        int count = 0;
        for (int i = left; i <= right; i++) {
            int bits = Integer.bitCount(i);
            if ((665772 & (1 << bits)) != 0) {
                count++;
            }
        }
        return count;
    }
}