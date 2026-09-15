class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int countL = 0;
        int countR = 0;
        int countWild = 0;

        for (int i = 0; i < moves.length(); i++) {
            char c = moves.charAt(i);
            if (c == 'L') {
                countL++;
            } else if (c == 'R') {
                countR++;
            } else {
                countWild++;
            }
        }

        return Math.abs(countL - countR) + countWild;
    }
}