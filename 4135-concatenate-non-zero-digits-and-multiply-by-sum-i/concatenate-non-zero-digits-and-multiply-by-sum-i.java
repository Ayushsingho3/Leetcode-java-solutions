class Solution {
    public long sumAndMultiply(int n) {
        long x = 0;
        long sum = 0;
        long multiplier = 1;
        int temp = Math.abs(n);
        
        while (temp > 0) {
            int digit = temp % 10;
            if (digit != 0) {
                x = x + digit * multiplier;
                multiplier *= 10;
                sum += digit;
            }
            temp /= 10;
        }
        
        long result = x * sum;
        return n < 0 ? -result : result;
    }
}