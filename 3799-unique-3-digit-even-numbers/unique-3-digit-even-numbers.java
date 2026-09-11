class Solution {
    public int totalNumbers(int[] digits) {
        int[] freq = new int[10];
        for (int d : digits) {
            freq[d]++;
        }

        int count = 0;
        for (int num = 100; num <= 998; num += 2) {
            int d1 = num / 100;
            int d2 = (num / 10) % 10;
            int d3 = num % 10;

            freq[d1]--;
            freq[d2]--;
            freq[d3]--;

            if (freq[d1] >= 0 && freq[d2] >= 0 && freq[d3] >= 0) {
                count++;
            }

            freq[d1]++;
            freq[d2]++;
            freq[d3]++;
        }

        return count;
    }
}