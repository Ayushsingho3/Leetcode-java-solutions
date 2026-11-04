import java.util.*;

class Solution {
    private static final long INF = (long) 1e18;

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        List<Long> windowCosts = getWindowCosts(nums, x);
        Long[][] memo = new Long[n + 1][k + 1];
        return solve(0, k, windowCosts, x, nums.length, memo);
    }

    private long solve(int i, int kLeft, List<Long> windowCosts, int x, int n, Long[][] memo) {
        if (kLeft == 0) return 0;
        if (i > n - kLeft * x) return INF;
        if (memo[i][kLeft] != null) return memo[i][kLeft];

        long skip = solve(i + 1, kLeft, windowCosts, x, n, memo);

        long take = INF;
        if (i + x <= n) {
            long cost = windowCosts.get(i);
            long nextCost = solve(i + x, kLeft - 1, windowCosts, x, n, memo);
            if (nextCost != INF) {
                take = cost + nextCost;
            }
        }

        return memo[i][kLeft] = Math.min(skip, take);
    }

    private List<Long> getWindowCosts(int[] nums, int x) {
        List<Long> costs = new ArrayList<>();
        TreeMap<Integer, Integer> lower = new TreeMap<>();
        TreeMap<Integer, Integer> upper = new TreeMap<>();
        long lowerSum = 0, upperSum = 0;
        int lowerSize = 0, upperSize = 0;

        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            if (lower.isEmpty() || val <= lower.lastKey()) {
                lower.put(val, lower.getOrDefault(val, 0) + 1);
                lowerSum += val;
                lowerSize++;
            } else {
                upper.put(val, upper.getOrDefault(val, 0) + 1);
                upperSum += val;
                upperSize++;
            }

            if (i >= x) {
                int out = nums[i - x];
                if (lower.containsKey(out)) {
                    lowerSum -= out;
                    lowerSize--;
                    if (lower.put(out, lower.get(out) - 1) == 1) lower.remove(out);
                } else {
                    upperSum -= out;
                    upperSize--;
                    if (upper.put(out, upper.get(out) - 1) == 1) upper.remove(out);
                }
            }

            while (lowerSize < upperSize) {
                int key = upper.firstKey();
                upperSum -= key; upperSize--;
                if (upper.put(key, upper.get(key) - 1) == 1) upper.remove(key);
                lower.put(key, lower.getOrDefault(key, 0) + 1);
                lowerSum += key; lowerSize++;
            }
            while (lowerSize > upperSize + 1) {
                int key = lower.lastKey();
                lowerSum -= key; lowerSize--;
                if (lower.put(key, lower.get(key) - 1) == 1) lower.remove(key);
                upper.put(key, upper.getOrDefault(key, 0) + 1);
                upperSum += key; upperSize++;
            }

            if (i >= x - 1) {
                int median = lower.lastKey();
                long ops = (1L * median * lowerSize - lowerSum) + (upperSum - 1L * median * upperSize);
                costs.add(ops);
            }
        }
        return costs;
    }
}