import java.util.Arrays;

class Solution {
    public int[] sortByBits(int[] arr) {
        Integer[] boxedArr = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            boxedArr[i] = arr[i];
        }
        
        Arrays.sort(boxedArr, (a, b) -> {
            int bitCountA = Integer.bitCount(a);
            int bitCountB = Integer.bitCount(b);
            if (bitCountA == bitCountB) {
                return a - b;
            }
            return bitCountA - bitCountB;
        });
        
        for (int i = 0; i < arr.length; i++) {
            arr[i] = boxedArr[i];
        }
        
        return arr;
    }
}