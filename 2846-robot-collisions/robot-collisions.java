import java.util.*;

class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        
        Arrays.sort(indices, (a, b) -> Integer.compare(positions[a], positions[b]));
        
        Deque<Integer> stack = new ArrayDeque<>();
        
        for (int i : indices) {
            if (directions.charAt(i) == 'R') {
                stack.push(i);
            } else {
                boolean survived = true;
                while (!stack.isEmpty() && directions.charAt(stack.peek()) == 'R') {
                    int top = stack.peek();
                    if (healths[top] < healths[i]) {
                        stack.pop();
                        healths[i] -= 1;
                    } else if (healths[top] > healths[i]) {
                        healths[top] -= 1;
                        survived = false;
                        break;
                    } else {
                        stack.pop();
                        survived = false;
                        break;
                    }
                }
                if (survived) {
                    stack.push(i);
                }
            }
        }
        
        List<Integer> survivors = new ArrayList<>(stack);
        Collections.sort(survivors);
        
        List<Integer> ans = new ArrayList<>();
        for (int i : survivors) {
            ans.add(healths[i]);
        }
        
        return ans;
    }
}