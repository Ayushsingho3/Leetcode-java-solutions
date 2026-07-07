import java.util.Arrays;

class Solution {
    public int longestBalanced(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        
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
        
        SegmentTree st = new SegmentTree(n);
        int maxLen = 0;
        
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            int id = Arrays.binarySearch(temp, 0, m, num);
            
            int prev = lastPos[id];
            int val = (num % 2 == 0) ? 1 : -1;
            
            st.update(1, 0, n - 1, prev + 1, i, val);
            
            int firstZeroIdx = st.queryFirstZero(1, 0, n - 1, 0, i);
            
            if (firstZeroIdx != -1) {
                int len = i - firstZeroIdx + 1;
                if (len > maxLen) {
                    maxLen = len;
                }
            }
            
            lastPos[id] = i;
        }
        
        return maxLen;
    }
    
    class SegmentTree {
        int[] minVal;
        int[] maxVal;
        int[] lazy;

        public SegmentTree(int n) {
            minVal = new int[4 * n];
            maxVal = new int[4 * n];
            lazy = new int[4 * n];
        }

        public void pushDown(int node) {
            if (lazy[node] != 0) {
                int left = node * 2;
                int right = node * 2 + 1;
                
                lazy[left] += lazy[node];
                minVal[left] += lazy[node];
                maxVal[left] += lazy[node];
                
                lazy[right] += lazy[node];
                minVal[right] += lazy[node];
                maxVal[right] += lazy[node];
                
                lazy[node] = 0;
            }
        }

        public void pushUp(int node) {
            minVal[node] = Math.min(minVal[node * 2], minVal[node * 2 + 1]);
            maxVal[node] = Math.max(maxVal[node * 2], maxVal[node * 2 + 1]);
        }

        public void update(int node, int l, int r, int ql, int qr, int val) {
            if (ql <= l && r <= qr) {
                minVal[node] += val;
                maxVal[node] += val;
                lazy[node] += val;
                return;
            }
            pushDown(node);
            int mid = l + (r - l) / 2;
            if (ql <= mid) {
                update(node * 2, l, mid, ql, qr, val);
            }
            if (qr > mid) {
                update(node * 2 + 1, mid + 1, r, ql, qr, val);
            }
            pushUp(node);
        }

        public int queryFirstZero(int node, int l, int r, int ql, int qr) {
            if (l > qr || r < ql || minVal[node] > 0 || maxVal[node] < 0) {
                return -1;
            }
            if (l == r) {
                return l;
            }
            pushDown(node);
            int mid = l + (r - l) / 2;
            
            int leftRes = queryFirstZero(node * 2, l, mid, ql, qr);
            if (leftRes != -1) return leftRes;
            
            return queryFirstZero(node * 2 + 1, mid + 1, r, ql, qr);
        }
    }
}