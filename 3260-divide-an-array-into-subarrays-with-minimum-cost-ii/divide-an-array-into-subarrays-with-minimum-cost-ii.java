import java.util.TreeSet;

class Solution {
    public long minimumCost(int[] nums, int k, int dist) {
        int n = nums.length;
        int k_needed = k - 1;
        
        TreeSet<Integer> left = new TreeSet<>((a, b) -> nums[a] == nums[b] ? a - b : Integer.compare(nums[a], nums[b]));
        TreeSet<Integer> right = new TreeSet<>((a, b) -> nums[a] == nums[b] ? a - b : Integer.compare(nums[a], nums[b]));
        
        long currentSum = 0;
        
        int windowEnd = Math.min(n - 1, 1 + dist);
        for (int i = 1; i <= windowEnd; i++) {
            left.add(i);
            currentSum += nums[i];
            if (left.size() > k_needed) {
                int max_left = left.pollLast();
                currentSum -= nums[max_left];
                right.add(max_left);
            }
        }
        
        long minSum = currentSum;
        
        for (int i = windowEnd + 1; i < n; i++) {
            int outIndex = i - dist - 1;
            
            if (right.contains(outIndex)) {
                right.remove(outIndex);
            } else {
                left.remove(outIndex);
                currentSum -= nums[outIndex];
                if (!right.isEmpty()) {
                    int min_right = right.pollFirst();
                    left.add(min_right);
                    currentSum += nums[min_right];
                }
            }
            
            left.add(i);
            currentSum += nums[i];
            if (left.size() > k_needed) {
                int max_left = left.pollLast();
                currentSum -= nums[max_left];
                right.add(max_left);
            }
            
            minSum = Math.min(minSum, currentSum);
        }
        
        return nums[0] + minSum;
    }
}