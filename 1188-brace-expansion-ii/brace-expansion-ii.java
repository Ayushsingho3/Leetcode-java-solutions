import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

class Solution {
    private int ptr = 0;

    public List<String> braceExpansionII(String expression) {
        ptr = 0;
        Set<String> set = parseExpr(expression);
        List<String> result = new ArrayList<>(new TreeSet<>(set));
        return result;
    }

    private Set<String> parseExpr(String s) {
        Set<String> totalUnion = new HashSet<>();
        Set<String> currentConcat = new HashSet<>();
        currentConcat.add("");

        while (ptr < s.length() && s.charAt(ptr) != '}') {
            if (s.charAt(ptr) == ',') {
                totalUnion.addAll(currentConcat);
                currentConcat = new HashSet<>();
                currentConcat.add("");
                ptr++;
            } else if (s.charAt(ptr) == '{') {
                ptr++;
                Set<String> inner = parseExpr(s);
                ptr++;
                currentConcat = CartesianProduct(currentConcat, inner);
            } else {
                Set<String> letterSet = new HashSet<>();
                letterSet.add(String.valueOf(s.charAt(ptr)));
                ptr++;
                currentConcat = CartesianProduct(currentConcat, letterSet);
            }
        }

        totalUnion.addAll(currentConcat);
        return totalUnion;
    }

    private Set<String> CartesianProduct(Set<String> set1, Set<String> set2) {
        Set<String> result = new HashSet<>();
        for (String s1 : set1) {
            for (String s2 : set2) {
                result.add(s1 + s2);
            }
        }
        return result;
    }
}