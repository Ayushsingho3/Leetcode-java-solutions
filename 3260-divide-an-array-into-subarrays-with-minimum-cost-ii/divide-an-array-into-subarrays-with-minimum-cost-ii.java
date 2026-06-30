import java.util.TreeMap;

class Solution {
    public long minimumCost(int[] nums, int k, int dist) {
        Window win = new Window(k - 1);
        int n = nums.length;
        
        for (int i = 1; i <= dist + 1 && i < n; i++) {
            win.add(nums[i]);
        }
        
        long minCost = win.leftSum;
        
        for (int i = dist + 2; i < n; i++) {
            win.add(nums[i]);
            win.remove(nums[i - (dist + 1)]);
            minCost = Math.min(minCost, win.leftSum);
        }
        
        return nums[0] + minCost;
    }
    
    class Window {
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();
        int leftCount = 0;
        int rightCount = 0;
        long leftSum = 0;
        int limit;

        public Window(int limit) {
            this.limit = limit;
        }

        public void add(int val) {
            left.put(val, left.getOrDefault(val, 0) + 1);
            leftCount++;
            leftSum += val;

            if (leftCount > limit) {
                int maxLeft = left.lastKey();
                removeFromMap(left, maxLeft);
                leftCount--;
                leftSum -= maxLeft;
                
                right.put(maxLeft, right.getOrDefault(maxLeft, 0) + 1);
                rightCount++;
            }
        }

        public void remove(int val) {
            if (right.containsKey(val)) {
                removeFromMap(right, val);
                rightCount--;
            } else {
                removeFromMap(left, val);
                leftCount--;
                leftSum -= val;
            }

            if (leftCount < limit && rightCount > 0) {
                int minRight = right.firstKey();
                removeFromMap(right, minRight);
                rightCount--;
                
                left.put(minRight, left.getOrDefault(minRight, 0) + 1);
                leftCount++;
                leftSum += minRight;
            }
        }

        private void removeFromMap(TreeMap<Integer, Integer> map, int val) {
            int count = map.get(val);
            if (count == 1) {
                map.remove(val);
            } else {
                map.put(val, count - 1);
            }
        }
    }
}