import java.util.Arrays;

class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int count = 0;
        
        for (int x : arr1) {
            int idx = binarySearch(arr2, x);
            boolean valid = true;
            
            if (idx < arr2.length && Math.abs(arr2[idx] - x) <= d) {
                valid = false;
            }
            if (idx > 0 && Math.abs(arr2[idx - 1] - x) <= d) {
                valid = false;
            }
            
            if (valid) {
                count++;
            }
        }
        
        return count;
    }

    private int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}