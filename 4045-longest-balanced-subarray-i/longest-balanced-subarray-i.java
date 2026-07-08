class Solution {
    int[] minVal;
    int[] maxVal;
    int[] lazy;

    public int longestBalanced(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        int treeSize = 4 * n;
        minVal = new int[treeSize];
        maxVal = new int[treeSize];
        lazy = new int[treeSize];

        IntHashMap map = new IntHashMap(n);
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            int prev = map.updateAndGetPrev(num, i);
            int val = (num % 2 == 0) ? 1 : -1;

            update(1, 0, n - 1, prev + 1, i, val);

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

    private void pushDown(int node) {
        int lz = lazy[node];
        if (lz != 0) {
            int left = node << 1;
            int right = left | 1;

            lazy[left] += lz;
            minVal[left] += lz;
            maxVal[left] += lz;

            lazy[right] += lz;
            minVal[right] += lz;
            maxVal[right] += lz;

            lazy[node] = 0;
        }
    }

    private void update(int node, int l, int r, int ql, int qr, int val) {
        if (ql <= l && r <= qr) {
            minVal[node] += val;
            maxVal[node] += val;
            lazy[node] += val;
            return;
        }
        pushDown(node);
        int mid = l + (r - l) / 2;
        int left = node << 1;
        int right = left | 1;

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
        pushDown(node);
        int mid = l + (r - l) / 2;
        int left = node << 1;

        if (minVal[left] <= 0 && maxVal[left] >= 0) {
            return queryFirstZero(left, l, mid);
        } else {
            return queryFirstZero(left | 1, mid + 1, r);
        }
    }

    class IntHashMap {
        int[] keys;
        int[] vals;
        boolean[] used;
        int mask;

        public IntHashMap(int size) {
            int capacity = 1;
            while (capacity < size) capacity <<= 1;
            capacity <<= 1;
            mask = capacity - 1;
            keys = new int[capacity];
            vals = new int[capacity];
            used = new boolean[capacity];
            for (int i = 0; i < capacity; i++) {
                vals[i] = -1;
            }
        }

        public int updateAndGetPrev(int key, int newVal) {
            int idx = hash(key) & mask;
            while (used[idx]) {
                if (keys[idx] == key) {
                    int prev = vals[idx];
                    vals[idx] = newVal;
                    return prev;
                }
                idx = (idx + 1) & mask;
            }
            used[idx] = true;
            keys[idx] = key;
            vals[idx] = newVal;
            return -1;
        }

        private int hash(int x) {
            x = ((x >>> 16) ^ x) * 0x45d9f3b;
            x = ((x >>> 16) ^ x) * 0x45d9f3b;
            x = (x >>> 16) ^ x;
            return x;
        }
    }
}