import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        Integer[] indices = new Integer[n];
        
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        
        Arrays.sort(indices, (a, b) -> Integer.compare(positions[a], positions[b]));
        
        Deque<Integer> stack = new ArrayDeque<>();
        
        for (int currIdx : indices) {
            if (directions.charAt(currIdx) == 'R') {
                stack.push(currIdx);
            } else {
                boolean survived = true;
                
                while (!stack.isEmpty() && directions.charAt(stack.peek()) == 'R') {
                    int topIdx = stack.peek();
                    
                    if (healths[currIdx] > healths[topIdx]) {
                        healths[currIdx] -= 1;
                        healths[topIdx] = 0;
                        stack.pop();
                    } else if (healths[currIdx] < healths[topIdx]) {
                        healths[topIdx] -= 1;
                        healths[currIdx] = 0;
                        survived = false;
                        break;
                    } else {
                        healths[currIdx] = 0;
                        healths[topIdx] = 0;
                        stack.pop();
                        survived = false;
                        break;
                    }
                }
                
                if (survived) {
                    stack.push(currIdx);
                }
            }
        }
        
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (healths[i] > 0) {
                result.add(healths[i]);
            }
        }
        
        return result;
    }
}