import java.util.Arrays;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        int[] pos = new int[n];
        int[] comp = new int[n];
        int currentComp = 0;
        
        pos[arr[0][1]] = 0;
        comp[0] = currentComp;

        for (int i = 1; i < n; i++) {
            pos[arr[i][1]] = i;
            if (arr[i][0] - arr[i - 1][0] > maxDiff) {
                currentComp++;
            }
            comp[i] = currentComp;
        }

        int LOG = 19; 
        int[][] up = new int[n][LOG];
        int right = 0;

        for (int i = 0; i < n; i++) {
            while (right + 1 < n && arr[right + 1][0] - arr[i][0] <= maxDiff) {
                right++;
            }
            up[i][0] = right;
        }

        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                up[i][j] = up[up[i][j - 1]][j - 1];
            }
        }

        int q = queries.length;
        int[] ans = new int[q];

        for (int i = 0; i < q; i++) {
            int u = queries[i][0];
            int v = queries[i][1];

            if (u == v) {
                ans[i] = 0;
                continue;
            }

            int posU = pos[u];
            int posV = pos[v];

            if (comp[posU] != comp[posV]) {
                ans[i] = -1;
                continue;
            }

            if (posU > posV) {
                int temp = posU;
                posU = posV;
                posV = temp;
            }

            int jumps = 0;
            for (int j = LOG - 1; j >= 0; j--) {
                if (up[posU][j] < posV) {
                    posU = up[posU][j];
                    jumps += (1 << j);
                }
            }
            
            ans[i] = jumps + 1;
        }

        return ans;
    }
}