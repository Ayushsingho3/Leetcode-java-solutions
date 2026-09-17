import java.util.Arrays;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] minLenToI = new int[n];
        Arrays.fill(minLenToI, Integer.MAX_VALUE / 2);

        int minLen = Integer.MAX_VALUE / 2;
        int ans = Integer.MAX_VALUE / 2;

        int windowSum = 0;
        int left = 0;

        for (int right = 0; right < n; right++) {
            windowSum += arr[right];
            while (windowSum > target) {
                windowSum -= arr[left++];
            }
            if (windowSum == target) {
                int currLen = right - left + 1;
                if (left > 0 && minLenToI[left - 1] != Integer.MAX_VALUE / 2) {
                    ans = Math.min(ans, minLenToI[left - 1] + currLen);
                }
                minLen = Math.min(minLen, currLen);
            }
            minLenToI[right] = minLen;
        }

        return ans == Integer.MAX_VALUE / 2 ? -1 : ans;
    }
}