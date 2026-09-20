class Solution {
    public int reverseDegree(String s) {
        int reverseDegree = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int reversedValue = 26 - (c - 'a');
            int position = i + 1;
            reverseDegree += reversedValue * position;
        }

        return reverseDegree;
    }
}