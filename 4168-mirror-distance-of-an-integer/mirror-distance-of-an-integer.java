class Solution {
    public int mirrorDistance(int n) {
        int temp = Math.abs(n);
        int rev = 0;
        
        while (temp > 0) {
            rev = rev * 10 + temp % 10;
            temp /= 10;
        }
        
        if (n < 0) {
            rev = -rev;
        }
        
        return Math.abs(n - rev);
    }
}