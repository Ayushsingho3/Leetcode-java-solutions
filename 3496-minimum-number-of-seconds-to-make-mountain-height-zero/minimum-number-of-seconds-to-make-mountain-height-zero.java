class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        long low = 0;
        long minW = workerTimes[0];
        for (int w : workerTimes) {
            if (w < minW) {
                minW = w;
            }
        }
        
        long high = minW * (long) mountainHeight * (mountainHeight + 1L) / 2L;
        long ans = high;
        
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (check(mid, mountainHeight, workerTimes)) {
                ans = mid;
                high = mid - 1; 
            } else {
                low = mid + 1; 
            }
        }
        
        return ans;
    }
    
    private boolean check(long T, int mountainHeight, int[] workerTimes) {
        long reduced = 0;
        for (int w : workerTimes) {
            long maxVal = (2L * T) / w;
            
            long x = (long) Math.sqrt(maxVal);
            
            while (x * (x + 1) > maxVal) {
                x--;
            }
            
            while ((x + 1) * (x + 2) <= maxVal) {
                x++;
            }
            
            reduced += x;
            
            if (reduced >= mountainHeight) {
                return true;
            }
        }
        return reduced >= mountainHeight;
    }
}