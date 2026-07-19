import java.util.Arrays;

class Solution {
    public int[] sortByBits(int[] arr) {
        long[] combined = new long[arr.length];
        for (int i = 0; i < arr.length; i++) {
            combined[i] = ((long) Integer.bitCount(arr[i]) << 32) | (arr[i] & 0xFFFFFFFFL);
        }
        Arrays.sort(combined);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) combined[i];
        }
        return arr;
    }
}