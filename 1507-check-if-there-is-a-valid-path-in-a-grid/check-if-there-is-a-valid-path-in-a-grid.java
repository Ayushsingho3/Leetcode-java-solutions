import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public boolean hasValidPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][][] PIPES = {
            {},
            {{0, -1}, {0, 1}},
            {{-1, 0}, {1, 0}},
            {{0, -1}, {1, 0}},
            {{0, 1}, {1, 0}},
            {{0, -1}, {-1, 0}},
            {{0, 1}, {-1, 0}}
        };

        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];

            if (r == m - 1 && c == n - 1) {
                return true;
            }

            int pipeType = grid[r][c];
            for (int[] dir : PIPES[pipeType]) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc]) {
                    int nextPipeType = grid[nr][nc];
                    boolean connectsBack = false;

                    for (int[] nextDir : PIPES[nextPipeType]) {
                        if (nextDir[0] == -dir[0] && nextDir[1] == -dir[1]) {
                            connectsBack = true;
                            break;
                        }
                    }

                    if (connectsBack) {
                        visited[nr][nc] = true;
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
        }

        return false;
    }
}