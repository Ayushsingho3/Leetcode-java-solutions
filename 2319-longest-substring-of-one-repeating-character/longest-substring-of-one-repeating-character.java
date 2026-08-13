class Solution {
    class Node {
        int preLen, sufLen, maxLen, size;
        char preChar, sufChar;
    }
    
    Node[] tree;
    char[] arr;
    
    private void merge(int node, int left, int right) {
        Node l = tree[left];
        Node r = tree[right];
        
        tree[node].size = l.size + r.size;
        tree[node].preChar = l.preChar;
        tree[node].sufChar = r.sufChar;
        
        tree[node].preLen = l.preLen;
        if (l.preLen == l.size && l.preChar == r.preChar) {
            tree[node].preLen += r.preLen;
        }
        
        tree[node].sufLen = r.sufLen;
        if (r.sufLen == r.size && r.sufChar == l.sufChar) {
            tree[node].sufLen += l.sufLen;
        }
        
        tree[node].maxLen = Math.max(l.maxLen, r.maxLen);
        if (l.sufChar == r.preChar) {
            tree[node].maxLen = Math.max(tree[node].maxLen, l.sufLen + r.preLen);
        }
    }
    
    private void build(int node, int start, int end) {
        tree[node] = new Node();
        if (start == end) {
            tree[node].preLen = 1;
            tree[node].sufLen = 1;
            tree[node].maxLen = 1;
            tree[node].size = 1;
            tree[node].preChar = arr[start];
            tree[node].sufChar = arr[start];
            return;
        }
        int mid = start + (end - start) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);
        merge(node, 2 * node, 2 * node + 1);
    }
    
    private void update(int node, int start, int end, int idx, char c) {
        if (start == end) {
            arr[idx] = c;
            tree[node].preChar = c;
            tree[node].sufChar = c;
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, c);
        } else {
            update(2 * node + 1, mid + 1, end, idx, c);
        }
        merge(node, 2 * node, 2 * node + 1);
    }

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        arr = s.toCharArray();
        tree = new Node[4 * n + 1];
        build(1, 0, n - 1);
        
        int k = queryIndices.length;
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            update(1, 0, n - 1, queryIndices[i], queryCharacters.charAt(i));
            ans[i] = tree[1].maxLen;
        }
        return ans;
    }
}