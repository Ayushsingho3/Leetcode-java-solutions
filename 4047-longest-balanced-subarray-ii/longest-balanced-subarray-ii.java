import java.util.Arrays;

class Solution {
    private int[] minVal;
    private int[] maxVal;
    private int[] lazy;

    public int longestBalanced(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        int treeSize = (n << 2) + 1;
        minVal = new int[treeSize];
        maxVal = new int[treeSize];
        lazy = new int[treeSize];

        int min = nums[0];
        int max = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] < min) min = nums[i];
            else if (nums[i] > max) max = nums[i];
        }

        long range = (long) max - (long) min;
        boolean direct = range <= 500000;

        int[] lastPos;
        int[] temp = null;
        int m = 0;

        if (direct) {
            lastPos = new int[(int) range + 1];
            Arrays.fill(lastPos, -1);
        } else {
            temp = nums.clone();
            Arrays.sort(temp);
            for (int i = 0; i < n; i++) {
                if (i == 0 || temp[i] != temp[i - 1]) {
                    temp[m++] = temp[i];
                }
            }
            lastPos = new int[m];
            Arrays.fill(lastPos, -1);
        }

        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            int id;

            if (direct) {
                id = num - min;
            } else {
                int low = 0;
                int high = m - 1;
                id = 0;
                while (low <= high) {
                    int mid = (low + high) >>> 1;
                    if (temp[mid] < num) {
                        low = mid + 1;
                    } else if (temp[mid] > num) {
                        high = mid - 1;
                    } else {
                        id = mid;
                        break;
                    }
                }
            }

            int prev = lastPos[id];
            lastPos[id] = i;

            int val = (num % 2 == 0) ? 1 : -1;

            if (prev + 1 <= i) {
                update(1, 0, n - 1, prev + 1, i, val);
            }

            int firstZero = queryFirstZero(1, 0, n - 1, i);
            if (firstZero != -1) {
                int len = i - firstZero + 1;
                if (len > maxLen) {
                    maxLen = len;
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

        int left = node << 1;
        int right = left | 1;
        int lz = lazy[node];

        if (lz != 0) {
            lazy[left] += lz;
            minVal[left] += lz;
            maxVal[left] += lz;

            lazy[right] += lz;
            minVal[right] += lz;
            maxVal[right] += lz;

            lazy[node] = 0;
        }

        int mid = (l + r) >>> 1;

        if (ql <= mid) {
            update(left, l, mid, ql, qr, val);
        }
        if (qr > mid) {
            update(right, mid + 1, r, ql, qr, val);
        }

        int lMin = minVal[left];
        int rMin = minVal[right];
        minVal[node] = lMin < rMin ? lMin : rMin;

        int lMax = maxVal[left];
        int rMax = maxVal[right];
        maxVal[node] = lMax > rMax ? lMax : rMax;
    }

    private int queryFirstZero(int node, int l, int r, int qr) {
        if (l > qr || minVal[node] > 0 || maxVal[node] < 0) {
            return -1;
        }
        if (l == r) {
            return l;
        }

        int left = node << 1;
        int right = left | 1;
        int lz = lazy[node];

        if (lz != 0) {
            lazy[left] += lz;
            minVal[left] += lz;
            maxVal[left] += lz;

            lazy[right] += lz;
            minVal[right] += lz;
            maxVal[right] += lz;

            lazy[node] = 0;
        }

        int mid = (l + r) >>> 1;

        int res = queryFirstZero(left, l, mid, qr);
        if (res != -1) {
            return res;
        }

        return queryFirstZero(right, mid + 1, r, qr);
    }
}