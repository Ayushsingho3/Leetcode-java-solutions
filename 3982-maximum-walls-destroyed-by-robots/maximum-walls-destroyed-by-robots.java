import java.util.Arrays;

class Solution {
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;
        
        int[][] r = new int[n][2];
        for (int i = 0; i < n; i++) {
            r[i][0] = robots[i];
            r[i][1] = distance[i];
        }
        Arrays.sort(r, (a, b) -> Integer.compare(a[0], b[0]));
        
        Arrays.sort(walls);
        
        int[] dp = new int[2];
        
        int[] bounds0 = getBounds(walls, Integer.MIN_VALUE, r[0][0] - 1);
        int start0 = bounds0[0], end0 = bounds0[1];
        int count0L = 0;
        if (start0 <= end0) {
            int minL = lowerBound(walls, start0, end0, r[0][0] - r[0][1]);
            if (minL <= end0) {
                count0L = end0 - minL + 1;
            }
        }
        
        int wallsAtR0 = countEquals(walls, r[0][0]);
        dp[0] = count0L + wallsAtR0;
        dp[1] = wallsAtR0;
        
        for (int i = 1; i < n; i++) {
            int prevX = r[i-1][0];
            int prevD = r[i-1][1];
            int currX = r[i][0];
            int currD = r[i][1];
            
            int[] bounds = getBounds(walls, prevX + 1, currX - 1);
            int start = bounds[0], end = bounds[1];
            
            int wallsAtCurr = countEquals(walls, currX);
            
            int costLL = 0, costLR = 0, costRL = 0, costRR = 0;
            if (start <= end) {
                int maxR = upperBound(walls, start, end, prevX + prevD);
                int minL = lowerBound(walls, start, end, currX - currD);
                
                costLR = 0; 
                if (minL <= end) costLL = end - minL + 1;
                if (maxR >= start) costRR = maxR - start + 1;
                if (maxR >= minL) {
                    costRL = end - start + 1; 
                } else {
                    if (maxR >= start) costRL += maxR - start + 1;
                    if (minL <= end) costRL += end - minL + 1;
                }
            }
            
            int nextDp0 = Math.max(dp[0] + costLL, dp[1] + costRL) + wallsAtCurr;
            int nextDp1 = Math.max(dp[0] + costLR, dp[1] + costRR) + wallsAtCurr;
            
            dp[0] = nextDp0;
            dp[1] = nextDp1;
        }
        
        int lastX = r[n-1][0];
        int lastD = r[n-1][1];
        
        int[] boundsN = getBounds(walls, lastX + 1, Integer.MAX_VALUE);
        int startN = boundsN[0], endN = boundsN[1];
        int countNR = 0;
        if (startN <= endN) {
            int maxR = upperBound(walls, startN, endN, lastX + lastD);
            if (maxR >= startN) {
                countNR = maxR - startN + 1;
            }
        }
        
        return Math.max(dp[0], dp[1] + countNR);
    }
    
    private int[] getBounds(int[] walls, int minVal, int maxVal) {
        int start = lowerBound(walls, 0, walls.length - 1, minVal);
        int end = upperBound(walls, 0, walls.length - 1, maxVal);
        return new int[]{start, end};
    }
    
    private int lowerBound(int[] arr, int start, int end, int val) {
        int ans = end + 1;
        int l = start, r = end;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] >= val) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }
    
    private int upperBound(int[] arr, int start, int end, int val) {
        int ans = start - 1;
        int l = start, r = end;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] <= val) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }
    
    private int countEquals(int[] arr, int val) {
        int start = lowerBound(arr, 0, arr.length - 1, val);
        if (start < arr.length && arr[start] == val) {
            int end = upperBound(arr, 0, arr.length - 1, val);
            return end - start + 1; 
        }
        return 0;
    }
}