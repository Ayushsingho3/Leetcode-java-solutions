class Solution {
    public int countPrimeSetBits(int left, int right) {
        int count = 0;
        for (int i = left; i <= right; i++) {
            if ((665772 & (1 << Integer.bitCount(i))) != 0) {
                count++;
            }
        }
        return count;
    }
}