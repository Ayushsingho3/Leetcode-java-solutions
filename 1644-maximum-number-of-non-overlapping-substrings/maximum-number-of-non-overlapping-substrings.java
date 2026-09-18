import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            int ch = s.charAt(i) - 'a';
            if (first[ch] == -1) {
                first[ch] = i;
            }
            last[ch] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            if (first[i] == -1) continue;

            int left = first[i];
            int right = last[i];
            boolean isValid = true;

            for (int j = left; j <= right; j++) {
                int ch = s.charAt(j) - 'a';
                if (first[ch] < left) {
                    isValid = false;
                    break;
                }
                right = Math.max(right, last[ch]);
            }

            if (isValid) {
                intervals.add(new int[]{left, right});
            }
        }

        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));

        List<String> result = new ArrayList<>();
        int lastEnd = -1;

        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];

            if (start > lastEnd) {
                result.add(s.substring(start, end + 1));
                lastEnd = end;
            }
        }

        return result;
    }
}