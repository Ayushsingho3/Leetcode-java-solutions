class Solution {
    public long findKthSmallest(int[] coins, int k) {
        long minCoin = coins[0];
        for (int c : coins) {
            minCoin = Math.min(minCoin, c);
        }
        
        long l = 1;
        long r = minCoin * k;

        while (l < r) {
            long mid = l + (r - l) / 2;
            if (count(mid, coins) < k) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        
        return l;
    }

    private long count(long mid, int[] coins) {
        long res = 0;
        int n = coins.length;
        
        for (int i = 1; i < (1 << n); i++) {
            long currentLcm = 1;
            int bits = 0;
            boolean overflow = false;
            
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    long g = gcd(currentLcm, coins[j]);
                    if (currentLcm / g > mid / coins[j]) {
                        overflow = true;
                        break;
                    }
                    currentLcm = (currentLcm / g) * coins[j];
                    bits++;
                }
            }
            
            if (!overflow) {
                if (bits % 2 == 1) {
                    res += mid / currentLcm;
                } else {
                    res -= mid / currentLcm;
                }
            }
        }
        
        return res;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}