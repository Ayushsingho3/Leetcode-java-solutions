import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public String makeLargestSpecial(String s) {
        int count = 0;
        int i = 0;
        List<String> res = new ArrayList<>();
        
        for (int j = 0; j < s.length(); j++) {
            if (s.charAt(j) == '1') {
                count++;
            } else {
                count--;
            }
            
            if (count == 0) {
                String inner = s.substring(i + 1, j);
                res.add("1" + makeLargestSpecial(inner) + "0");
                i = j + 1;
            }
        }
        
        Collections.sort(res, Collections.reverseOrder());
        
        return String.join("", res);
    }
}