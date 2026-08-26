class Solution {
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        int minDistance = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            if (words[i].equals(target)) {
                int dist = Math.abs(i - startIndex);
                int circularDist = n - dist;
                minDistance = Math.min(minDistance, Math.min(dist, circularDist));
            }
        }
        
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }
}