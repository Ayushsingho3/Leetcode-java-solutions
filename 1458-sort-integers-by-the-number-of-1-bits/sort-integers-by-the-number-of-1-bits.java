class Solution {
    public int[] sortByBits(int[] arr) {
        long[] encoded = new long[arr.length];
        for (int i = 0; i < arr.length; i++) {
            encoded[i] = ((long) Integer.bitCount(arr[i]) << 32) | arr[i];
        }
        java.util.Arrays.sort(encoded);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) encoded[i];
        }
        return arr;
    }
}