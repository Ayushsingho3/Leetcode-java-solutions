import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int minMoves(String[] grid, int energy) {
        int m = grid.length;
        int n = grid[0].length();
        
        int startR = -1;
        int startC = -1;
        int numL = 0;
        
        int[][] lId = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(lId[i], -1);
            for (int j = 0; j < n; j++) {
                char cell = grid[i].charAt(j);
                if (cell == 'S') {
                    startR = i;
                    startC = j;
                } else if (cell == 'L') {
                    lId[i][j] = numL++;
                }
            }
        }
        
        if (numL == 0) {
            return 0;
        }
        
        int finalMask = (1 << numL) - 1;
        int[][][] maxE = new int[m][n][1 << numL];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(maxE[i][j], -1);
            }
        }
        
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{startR, startC, 0, energy, 0});
        maxE[startR][startC][0] = energy;
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];
            int mask = curr[2];
            int e = curr[3];
            int steps = curr[4];
            
            if (e == 0 && grid[r].charAt(c) != 'R') {
                continue;
            }
            
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr].charAt(nc) != 'X') {
                    int ne = e - 1;
                    if (ne < 0) continue;
                    
                    char nextCell = grid[nr].charAt(nc);
                    if (nextCell == 'R') {
                        ne = energy;
                    }
                    
                    int nmask = mask;
                    if (nextCell == 'L') {
                        nmask |= (1 << lId[nr][nc]);
                    }
                    
                    if (nmask == finalMask) {
                        return steps + 1;
                    }
                    
                    if (ne > maxE[nr][nc][nmask]) {
                        maxE[nr][nc][nmask] = ne;
                        q.offer(new int[]{nr, nc, nmask, ne, steps + 1});
                    }
                }
            }
        }
        
        return -1;
    }
}