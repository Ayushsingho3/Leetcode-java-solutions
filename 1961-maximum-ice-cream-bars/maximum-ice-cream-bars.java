class Solution {
    public int maxIceCream(int[] costs, int coins) {
        int maxCost = 0;
        for (int cost : costs) {
            maxCost = Math.max(maxCost, cost);
        }

        int[] freq = new int[maxCost + 1];
        for (int cost : costs) {
            freq[cost]++;
        }

        int bars = 0;
        for (int cost = 1; cost <= maxCost; cost++) {
            if (freq[cost] == 0) {
                continue;
            }
            if (coins < cost) {
                break;
            }
            int count = Math.min(freq[cost], coins / cost);
            bars += count;
            coins -= count * cost;
        }

        return bars;
    }
}