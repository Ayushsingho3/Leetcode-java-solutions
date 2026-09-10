import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> result = new ArrayList<>();
        int wordLen = queries[0].length();

        for (String q : queries) {
            for (String d : dictionary) {
                int diff = 0;
                for (int i = 0; i < wordLen; i++) {
                    if (q.charAt(i) != d.charAt(i)) {
                        diff++;
                        if (diff > 2) {
                            break;
                        }
                    }
                }
                if (diff <= 2) {
                    result.add(q);
                    break;
                }
            }
        }

        return result;
    }
}