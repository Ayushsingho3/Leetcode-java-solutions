import java.util.Arrays;

class Solution {
    int[] minVal;
    int[] maxVal;
    int[] lazy;

    public int longestBalanced(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        int treeSize = 4 * n + 1;
        minVal = new int[treeSize];
        maxVal = new int[treeSize];
        lazy = new int[treeSize];

        int[] temp = nums.clone();
        Arrays.sort(temp);
        int m = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || temp[i] != temp[i - 1]) {
                temp[m++] = temp[i];
            }
        }
        
        int[] lastPos = new int[m];
        Arrays.fill(lastPos, -1);

        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            int id = Arrays.binarySearch(temp, 0, m, num);
            
            int prev = lastPos[id];
            lastPos[id] = i;
            
            int val = (num % 2 == 0) ? 1 : -1;

            if (prev + 1 <= i) {
                update(1, 0, n - 1, prev + 1, i, val);
            }

            if (minVal[1] <= 0 && maxVal[1] >= 0) {
                int firstZero = queryFirstZero(1, 0, n - 1);
                if (firstZero <= i) {
                    int len = i - firstZero + 1;
                    if (len > maxLen) {
                        maxLen = len;
                    }
                }
            }
        }

        return maxLen;
    }

    private void update(int node, int l, int r, int ql, int qr, int val) {
        if (ql <= l && r <= qr) {
            minVal[node] += val;
            maxVal[node] += val;
            lazy[node] += val;
            return;
        }
        
        int lz = lazy[node];
        int left = node << 1;
        int right = left | 1;
        
        if (lz != 0) {
            lazy[left] += lz;
            minVal[left] += lz;
            maxVal[left] += lz;
            
            lazy[right] += lz;
            minVal[right] += lz;
            maxVal[right] += lz;
            
            lazy[node] = 0;
        }
        
        int mid = (l + r) >> 1;

        if (ql <= mid) {
            update(left, l, mid, ql, qr, val);
        }
        if (qr > mid) {
            update(right, mid + 1, r, ql, qr, val);
        }

        minVal[node] = minVal[left] < minVal[right] ? minVal[left] : minVal[right];
        maxVal[node] = maxVal[left] > maxVal[right] ? maxVal[left] : maxVal[right];
    }

    private int queryFirstZero(int node, int l, int r) {
        if (l == r) {
            return l;
        }
        
        int lz = lazy[node];
        int left = node << 1;
        int right = left | 1;
        
        if (lz != 0) {
            lazy[left] += lz;
            minVal[left] += lz;
            maxVal[left] += lz;
            
            lazy[right] += lz;
            minVal[right] += lz;
            maxVal[right] += lz;
            
            lazy[node] = 0;
        }

        int mid = (l + r) >> 1;

        if (minVal[left] <= 0 && maxVal[left] >= 0) {
            return queryFirstZero(left, l, mid);
        } else {
            return queryFirstZero(right, mid + 1, r);
        }
    }
}