public class Solution {
    
    public int smallestNumber(int n, int t) {
        while (true) {
            if (getDigitProduct(n) % t == 0) {
                return n;
            }
            n++;
        }
    }
    
    private int getDigitProduct(int num) {
        if (num == 0) return 0;
        
        int product = 1;
        while (num > 0) {
            product *= (num % 10);
            
            if (product == 0) {
                return 0;
            }
            
            num /= 10;
        }
        return product;
    }
}