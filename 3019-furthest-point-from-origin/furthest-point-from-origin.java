class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int countL = 0;
        int countR = 0;
        int countWildcard = 0;

        for (char c : moves.toCharArray()) {
            if (c == 'L') {
                countL++;
            } else if (c == 'R') {
                countR++;
            } else {
                countWildcard++;
            }
        }

        return Math.abs(countL - countR) + countWildcard;
    }
}