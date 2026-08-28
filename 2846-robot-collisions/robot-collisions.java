import java.util.*;

class Solution {
    class Robot {
        int id;
        int pos;
        int health;
        char dir;

        Robot(int id, int pos, int health, char dir) {
            this.id = id;
            this.pos = pos;
            this.health = health;
            this.dir = dir;
        }
    }

    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        Robot[] robots = new Robot[n];
        for (int i = 0; i < n; i++) {
            robots[i] = new Robot(i, positions[i], healths[i], directions.charAt(i));
        }
        
        Arrays.sort(robots, (a, b) -> Integer.compare(a.pos, b.pos));
        
        Deque<Robot> stack = new ArrayDeque<>();
        for (Robot robot : robots) {
            if (robot.dir == 'R') {
                stack.push(robot);
            } else {
                boolean survived = true;
                while (!stack.isEmpty() && stack.peek().dir == 'R') {
                    Robot top = stack.peek();
                    if (top.health < robot.health) {
                        stack.pop();
                        robot.health -= 1;
                    } else if (top.health > robot.health) {
                        top.health -= 1;
                        survived = false;
                        break;
                    } else {
                        stack.pop();
                        survived = false;
                        break;
                    }
                }
                if (survived) {
                    stack.push(robot);
                }
            }
        }
        
        List<Robot> survivors = new ArrayList<>(stack);
        survivors.sort((a, b) -> Integer.compare(a.id, b.id));
        
        List<Integer> result = new ArrayList<>();
        for (Robot r : survivors) {
            result.add(r.health);
        }
        
        return result;
    }
}