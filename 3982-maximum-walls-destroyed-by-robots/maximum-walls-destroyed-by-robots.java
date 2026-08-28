import java.util.*;

class Solution {
    class Robot implements Comparable<Robot> {
        long pos;
        long dist;
        Robot(long p, long d) { 
            pos = p; 
            dist = d; 
        }
        public int compareTo(Robot o) {
            return Long.compare(this.pos, o.pos);
        }
    }

    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;
        if (n == 0) return 0;
        
        Robot[] robs = new Robot[n];
        for (int i = 0; i < n; i++) {
            robs[i] = new Robot(robots[i], distance[i]);
        }
        Arrays.sort(robs);
        
        Arrays.sort(walls);
        int m = 0;
        for (int i = 0; i < walls.length; i++) {
            if (i == 0 || walls[i] != walls[i-1]) {
                walls[m++] = walls[i];
            }
        }
        int[] uniqWalls = Arrays.copyOf(walls, m);
        
        int baseDestroyed = 0;
        for (int i = 0; i < n; i++) {
            if (i > 0 && robs[i].pos == robs[i-1].pos) continue;
            if (binarySearch(uniqWalls, (int)robs[i].pos)) {
                baseDestroyed++;
            }
        }
        
        long[] dp = new long[2];
        dp[0] = countWalls(robs[0].pos - robs[0].dist, robs[0].pos - 1, uniqWalls);
        dp[1] = 0;
        
        for (int i = 1; i < n; i++) {
            long[] next_dp = new long[2];
            for (int curr_dir = 0; curr_dir < 2; curr_dir++) {
                long max_val = -1;
                for (int prev_dir = 0; prev_dir < 2; prev_dir++) {
                    long A = (prev_dir == 1) ? Math.min(robs[i-1].pos + robs[i-1].dist, robs[i].pos - 1) : robs[i-1].pos;
                    long B = (curr_dir == 0) ? Math.max(robs[i].pos - robs[i].dist, robs[i-1].pos + 1) : robs[i].pos;
                    
                    long covered = countWalls(robs[i-1].pos + 1, A, uniqWalls) + countWalls(B, robs[i].pos - 1, uniqWalls);
                    
                    if (A >= B) {
                        covered -= countWalls(B, A, uniqWalls);
                    }
                    
                    max_val = Math.max(max_val, dp[prev_dir] + covered);
                }
                next_dp[curr_dir] = max_val;
            }
            dp = next_dp;
        }
        
        long ans = Math.max(
            dp[0],
            dp[1] + countWalls(robs[n-1].pos + 1, robs[n-1].pos + robs[n-1].dist, uniqWalls)
        );
        
        return (int)(ans + baseDestroyed);
    }
    
    private boolean binarySearch(int[] arr, int target) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == target) return true;
            if (arr[mid] < target) l = mid + 1;
            else r = mid - 1;
        }
        return false;
    }
    
    private int countWalls(long L, long R, int[] walls) {
        if (L > R) return 0;
        int leftIdx = lowerBound(walls, L);
        int rightIdx = upperBound(walls, R) - 1;
        if (leftIdx <= rightIdx) {
            return rightIdx - leftIdx + 1;
        }
        return 0;
    }
    
    private int lowerBound(int[] arr, long target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] >= target) r = mid;
            else l = mid + 1;
        }
        return l;
    }
    
    private int upperBound(int[] arr, long target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] > target) r = mid;
            else l = mid + 1;
        }
        return l;
    }
}