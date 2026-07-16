import java.util.Arrays;

class Solution {
    public long gcdSum(int[] nums) {
        int n = nums.length;
        int[] prefixGcd = new int[n];
        int currentMax = 0;
        
        for (int i = 0; i < n; i++) {
            currentMax = Math.max(currentMax, nums[i]);
            prefixGcd[i] = gcd(nums[i], currentMax);
        }
        
        Arrays.sort(prefixGcd);
        
        long sum = 0;
        for (int i = 0; i < n / 2; i++) {
            sum += gcd(prefixGcd[i], prefixGcd[n - 1 - i]);
        }
        
        return sum;
    }
    
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}