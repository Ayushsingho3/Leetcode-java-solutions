import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final long MOD1 = 1_000_000_007L;
    private static final long MOD2 = 1_000_000_009L;
    private static final long BASE1 = 100_003L;
    private static final long BASE2 = 100_019L;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 1;
        }

        int[] arr = compress(nums);

        long[] pow1 = new long[n + 1];
        long[] pow2 = new long[n + 1];
        pow1[0] = 1;
        pow2[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow1[i] = (pow1[i - 1] * BASE1) % MOD1;
            pow2[i] = (pow2[i - 1] * BASE2) % MOD2;
        }

        Map<Long, Integer> counts = new HashMap<>();
        int low = 1;
        int high = n;
        int ans = n;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (hasUniqueOfLength(arr, mid, pow1, pow2, counts)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private int[] compress(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int uniqueCount = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || sorted[i] != sorted[i - 1]) {
                sorted[uniqueCount++] = sorted[i];
            }
        }

        int[] compressed = new int[n];
        for (int i = 0; i < n; i++) {
            int idx = Arrays.binarySearch(sorted, 0, uniqueCount, nums[i]);
            compressed[i] = idx + 1; // 1-based positive values
        }
        return compressed;
    }

    private boolean hasUniqueOfLength(
            int[] arr,
            int len,
            long[] pow1,
            long[] pow2,
            Map<Long, Integer> counts
    ) {
        int n = arr.length;
        counts.clear();

        long h1 = 0;
        long h2 = 0;
        long highPow1 = pow1[len - 1];
        long highPow2 = pow2[len - 1];

        for (int i = 0; i < len; i++) {
            h1 = (h1 * BASE1 + arr[i]) % MOD1;
            h2 = (h2 * BASE2 + arr[i]) % MOD2;
        }
        counts.put(pack(h1, h2), 1);

        for (int i = len; i < n; i++) {
            long outVal = arr[i - len];
            long inVal = arr[i];

            h1 = (h1 - (outVal * highPow1) % MOD1 + MOD1) % MOD1;
            h1 = (h1 * BASE1 + inVal) % MOD1;

            h2 = (h2 - (outVal * highPow2) % MOD2 + MOD2) % MOD2;
            h2 = (h2 * BASE2 + inVal) % MOD2;

            long key = pack(h1, h2);
            counts.put(key, counts.getOrDefault(key, 0) + 1);
        }

        for (int freq : counts.values()) {
            if (freq == 1) {
                return true;
            }
        }
        return false;
    }

    private long pack(long h1, long h2) {
        return (h1 << 32) | (h2 & 0xffffffffL);
    }
}